package org.forfun.concordance.sentence.detector

/**
 * Different detectors can be implemented. "Sentence" is a NLP problem and
 * different strategies have been implemented so far. So far just a simple
 * implementation was done using regular expression.
 */
sealed trait Detector {

  /**
   * Takes a paragraph and splits it in a list of sentences.
   * @param paragraph
   * @return
   */
  def getSentences(paragraph: String): BufferedIterator[String]

}


/**
 * This a type to represent a sentence detector using reg exp.
 */
trait DetectorRegExp extends Detector

/**
 * Another implementation for detecting sentences: http://www.attivio.com/blog/october-2008/doing-things-with-words-part-two-sentence-boun
 */
trait DetectorBoundaryDetection extends Detector
