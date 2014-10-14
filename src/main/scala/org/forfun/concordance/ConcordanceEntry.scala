package org.forfun.concordance

case class ConcordanceEntry(word: String, total: Int = 0, sentencesNumber: Seq[Int] = Seq.empty){
  override def toString() = s"$word {$total:${sentencesNumber.mkString(",")}}"
}
