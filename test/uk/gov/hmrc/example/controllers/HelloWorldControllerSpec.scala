/*
 * Copyright 2021 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.example.controllers


import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.{Configuration, Environment}
import uk.gov.hmrc.example.config.AppConfig
import uk.gov.hmrc.example.views.html.HelloWorldView


class HelloWorldControllerSpec extends AnyWordSpecLike with Matchers with GuiceOneAppPerSuite {
  private val fakeRequest = FakeRequest("GET", "/")
  private val env = Environment.simple()
  private val configuration = Configuration.load(env)
  private val mcc = stubMessagesControllerComponents()
  private val appConfig  = new AppConfig(configuration)
  private val template = app.injector.instanceOf[HelloWorldView]
  private val controller = new HelloWorldController(template, mcc, appConfig)

  "GET /" should {
    "return 200" in {
      val result = controller.helloWorld(fakeRequest)
      status(result) shouldBe Status.OK
    }

    "return HTML" in {
      val result = controller.helloWorld(fakeRequest)
      contentType(result) shouldBe Some("text/html")
      charset(result)     shouldBe Some("utf-8")
    }

  }
}
