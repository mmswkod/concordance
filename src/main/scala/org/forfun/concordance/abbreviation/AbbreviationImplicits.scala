package org.forfun.concordance.abbreviation

import scala.collection.immutable.HashSet

object AbbreviationImplicits {

  implicit object NaiveAbbreviationImpl extends NaiveAbbreviation{

    //TODO must contain a list of classical abbreviations
    private [this] val abbreviations: HashSet[String] = HashSet[String]("i.e.")

    override def isAbbreviation(abbreviation: String): Boolean = abbreviations.contains(abbreviation)
  }

}
