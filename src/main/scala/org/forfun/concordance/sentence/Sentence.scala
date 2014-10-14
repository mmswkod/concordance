package org.forfun.concordance.sentence

import java.io.File

import org.forfun.concordance.sentence.collection.SentenceSource
import org.forfun.concordance.sentence.detector.Detector


/**
 * Creates a sentence iterator using a specific detector found in the context.
 * The implementation for finding sentences is based in a paragraph approach. It
 * means it reads a set of lines that represents a paragraph and then extract the sentences.
 * On the future a faster approach can be implemented, where it can extract the sentences when
 * reading the stream(file bytes).
 */
object Sentence{

  /**
   *
   * @param file
   * @param detector
   * @tparam A
   * @return
   */
  def apply[A <: Detector](file: File) (implicit detector: A) = {
    val buffer = 8 * 1024
    val lines = io.Source.fromFile(file, buffer).getLines()
    SentenceSource(lines.buffered, detector)
  }

}


