package org.forfun.concordance.abbreviation

trait Abbreviation {

  def isAbbreviation(word: String): Boolean

}


trait NaiveAbbreviation extends Abbreviation