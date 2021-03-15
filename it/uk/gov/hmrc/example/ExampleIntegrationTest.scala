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

package uk.gov.hmrc.example

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import play.api.libs.ws.WSClient
import uk.gov.hmrc.integration.ServiceSpec

class ExampleIntegrationTest extends AnyWordSpecLike with Matchers with ServiceSpec  {

  def externalServices: Seq[String] = Seq()

  "This integration test" should {
    "connects to the hello-world page" in {

      val wsClient = app.injector.instanceOf[WSClient]

      val response = wsClient.url(resource("/platops-example-frontend-microservice/hello-world")).get.futureValue
      response.status shouldBe 200

    }
  }
}
