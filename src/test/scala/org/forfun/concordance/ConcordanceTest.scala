package org.forfun.concordance

import java.io.File


import org.forfun.concordance.sentence._
import org.forfun.concordance.sentence.detector.DetectorRegExp
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.forfun.concordance.sentence.detector.DetectorsImplicits._


class ConcordanceTest extends FeatureSpec with GivenWhenThen with ScalaFutures with EitherValues with Matchers{

  info("I want to be able to check all sentences from a specific input stream")

  feature("Check sentences") {
    scenario("Provided a text file wich has sentences") {

      Given("a file input")

      val file = new File("./src/test/resources/concordance2.txt")

      When("checking using the regexp detector")

      val words = Concordance(Sentence[DetectorRegExp](file))

      val concordance = words.words.map(x => x.toString()).mkString(",")

      Then("it confirms the expected concordance")

      val expectResult = "a {2:1,1},all {1:1},alphabetical {1:1},an {2:1,1},appeared {1:2},arbitrary {1:1},bonus {1:2},concordance {1:1},document {1:1},each {2:2,2},english {1:1},frequencies {1:1},generate {1:1},given {1:1},i.e. {1:1},in {2:1,2},label {1:2},labeled {1:1},list {1:1},numbers {1:2},occurrence {1:2},occurrences {1:1},of {1:1},program {1:1},sentence {1:2},text {1:1},that {1:1},the {1:2},which {1:2},will {1:1},with {2:1,2},word {3:1,1,2},write {1:1},written {1:1}"

      assert(expectResult == concordance)


    }
  }

}
