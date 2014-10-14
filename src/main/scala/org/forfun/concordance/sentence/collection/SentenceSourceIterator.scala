package org.forfun.concordance.sentence.collection

import org.forfun.concordance.sentence.detector.Detector

/**
 * A iterator to produce sentences from a input
 */
trait SentenceSourceIterator extends Iterator[String]{

  /** The sentence detector type */
  val detector: Detector

  /** iterator which contains a list of sentences */
  def sentences(): Iterator[String]

  /** The buffered iterator reading lines from a input stream */
  def linesSource(): BufferedIterator[String]

  /** loads the next group of sentences in case still there are more line(s) to be analyzed */
  def loadSentences(): BufferedIterator[String]


  /**
   * Tests whether this iterator can provide another sentence
   * @return
   */
  def hasNext: Boolean = {
    (sentences().hasNext, linesSource().hasNext) match{
      case (true, _) => true
      case (_, false) => false
      case _ => loadSentences.hasNext
    }

  }

  /**
   * Produces the next sentence of this iterator. In case the
   * sentence iterator is empty it checks the lines iterator.
   * @return
   */
  def next: String = {
    if (sentences.hasNext) sentences.next()
    else loadSentences.next()
  }


}
