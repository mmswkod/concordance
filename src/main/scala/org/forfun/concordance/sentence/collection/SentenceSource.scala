package org.forfun.concordance.sentence.collection

import org.forfun.concordance.sentence.detector.Detector

/**
 *
 * @param linesIterator
 * @param detector
 */
class SentenceSource(private var linesIterator: BufferedIterator[String], val detector: Detector) extends SentenceSourceIterator{

  private [this] var sentencesIterator: Iterator[String] = Iterator.empty

  def loadSentences = {
    val (paragraph, tail) = linesIterator.span(p => p.trim.nonEmpty)
    linesIterator = tail.dropWhile(p => p.trim.isEmpty).buffered
    sentencesIterator = detector.getSentences(paragraph.mkString(" ")).buffered
    sentencesIterator.buffered
  }

  override def linesSource(): BufferedIterator[String] = linesIterator

  /** iterator which contains a list of sentences */
  override def sentences(): Iterator[String] = sentencesIterator

}


/**
 * Companion object
 */
object SentenceSource {

  def apply(lines: BufferedIterator[String], detector: Detector) = new SentenceSource(lines.buffered, detector)

}
