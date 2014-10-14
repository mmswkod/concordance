package org.forfun.concordance.sentence.detector

/**
 * Holds all the implicits for detectors
 */
object DetectorsImplicits {

  /**
   * Using the regexp from http://en.wikipedia.org/wiki/Sentence_boundary_disambiguation
   */
  implicit object SimpleDetector extends DetectorRegExp {

    override def getSentences(paragraph: String): BufferedIterator[String] = {
      paragraph.split("((?<=[a-z0-9][.?!])|(?<=[a-z0-9][.?!]\\\"))(\\s|\\r\\n)(?=\\\"?[A-Z])").iterator.buffered
    }

  }


}
