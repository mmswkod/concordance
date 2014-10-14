package org.forfun.concordance

import org.forfun.concordance.abbreviation.NaiveAbbreviation
import org.forfun.concordance.sentence.detector.DetectorsImplicits._
import org.forfun.concordance.abbreviation.AbbreviationImplicits._
import scala.collection.immutable.{TreeMap}


/**
 *
 */
case class Words(words: Seq[ConcordanceEntry])

/**
 * Returns concordance for all sentences provided.
 * It is an alphabetical list of all words
 */
object Concordance {

  def apply(sentences: Iterator[String]) = {

    val concordanceEntries =
      sentences
        .zipWithIndex
        .flatMap{case (sentence, count) => sentence.toLowerCase.split("[\\s,?!:]+") map{ word =>

        val verified =
          if(word.endsWith(".") && implicitly[NaiveAbbreviation].isAbbreviation(word)) word
          else if(word.endsWith(".")) word.substring(0, word.length - 1)
          else word

        (verified, count+1)

      }
      }
        .foldLeft(TreeMap.empty[String, ConcordanceEntry]){ (counter, pair) =>
        counter + (pair._1 -> {
          val c = counter.getOrElse(pair._1, ConcordanceEntry(pair._1))
          c.copy(total = c.total + 1, sentencesNumber = c.sentencesNumber ++ Seq(pair._2))
        }
          )
      }.values.toSeq

    Words(concordanceEntries)

  }

}
