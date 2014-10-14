package org.forfun.concordance


import java.io.File

import org.forfun.concordance.sentence._
import org.forfun.concordance.sentence.detector.DetectorRegExp
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.forfun.concordance.sentence.detector.DetectorsImplicits._



class CheckSentenceTest extends FeatureSpec with GivenWhenThen with ScalaFutures with EitherValues with Matchers{

  info("I want to be able to check all sentences from a specific input stream")

  feature("Check sentences") {
    scenario("Provided a text file wich has sentences") {

      Given("a file input")

      val file = new File("./src/test/resources/concordance.txt")

      When("checking using the regexp detector")

      val sentences = Sentence[DetectorRegExp](file)

      Then("it confirms the number of sentences present in the file")

      assert(sentences.length == 4)


    }
  }

}
