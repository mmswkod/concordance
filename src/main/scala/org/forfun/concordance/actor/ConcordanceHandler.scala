package org.forfun.concordance.actor


import akka.actor._
import org.apache.commons.io.IOUtils
import org.forfun.concordance.{Words, ConcordanceEntry, Concordance}
import org.forfun.concordance.sentence.Sentence
import org.forfun.concordance.sentence.detector.DetectorRegExp
import spray.can.Http
import spray.json._
import scala.concurrent.duration._
import java.io._
import spray.http._
import spray.httpx.SprayJsonSupport._
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol._
import spray.json.DefaultJsonProtocol
import spray.io.CommandWrapper
import scala.collection._
import org.forfun.concordance.sentence.detector.DetectorsImplicits._


/**
 *
 */
object ConcordanceHandler{
  case object CloseChunk
  def props(client: ActorRef, start: ChunkedRequestStart): Props = Props(new ConcordanceHandler(client, start))
}


/**
 *
 * @param client
 * @param start
 */
class ConcordanceHandler (client: ActorRef, start: ChunkedRequestStart) extends Actor with ActorLogging {
  import ConcordanceHandler._

  //add the json converter
  implicit val concordanceEntryJson = jsonFormat3(ConcordanceEntry)
  implicit val wordsJson = jsonFormat1(Words)

  /** cancel any timeout to keep the connection alive while chunks are coming*/
  client ! CommandWrapper(SetRequestTimeout(Duration.Inf))

  /** The upload file will come as chunks and saved in a temporary folder. Once the file is processed it is deleted */
  val uploadFile = File.createTempFile("concordance-file", ".txt", new File("/tmp"))
  uploadFile.deleteOnExit()
  val output = new FileOutputStream(uploadFile)


  def receive = {

    /** start receiving message chunks*/
    case c: MessageChunk =>
      /** read each chunk and save it in a file... does not keep in memory */
      val bytes = c.data.toByteArray
      IOUtils.write(bytes, output)


    /**
     *  Finish reading all chunks now let's read the file using a lazy lines and
     * identify the sentences per paragraph. For each set of sentences per paragraph it process the words concordance
     */
    case e: ChunkedMessageEnd =>
      output.close()
      val sentences = Sentence[DetectorRegExp](uploadFile)
      val words = Concordance(sentences)

      // This implementation is just sending back the response in one unique chunk.
      // But it can be optimized to response in chunks. Taking advantage of HTTP 1.1
      client ! ChunkedResponseStart(
        HttpResponse(
          status = 200,
          entity = HttpEntity(
            contentType = ContentTypes.`application/json`,
            string = words.toJson.prettyPrint
          )
        )
      ).withAck(CloseChunk)

    case CloseChunk =>
      client ! ChunkedMessageEnd // tells to the client the chunks are done
      client ! CommandWrapper(SetRequestTimeout(2.seconds)) // reset timeout to original value

      uploadFile.delete()
      context.stop(self)

    /** in case connection is closed or lost */
    case x: Http.ConnectionClosed =>
      uploadFile.delete()
      client ! CommandWrapper(SetRequestTimeout(2.seconds)) // reset timeout to original value
      context.stop(self)

  }


}