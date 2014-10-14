package org.forfun.concordance

import java.io.{FileInputStream, BufferedInputStream, InputStream, File}


import akka.actor.{ActorSystem, Props}
import akka.io.IO
import org.forfun.concordance.actor.ConcordanceHttpActor
import spray.can.Http

/**
 * Main class to define and bind the service
 */
object Rest extends App{

  implicit val system = ActorSystem("concordance-service")

  val handler = system.actorOf(Props[ConcordanceHttpActor], name = "concordance-service-man")

  val config = new ServiceConfig().apiConfig

  IO(Http) ! Http.Bind(handler, interface = config.host, port = config.port)

}