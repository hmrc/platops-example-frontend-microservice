/*
 * Copyright 2018 HM Revenue & Customs
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

import uk.gov.hmrc.example.pages.HelloWorldPage
import uk.gov.hmrc.example.spec.BaseSpec

class HelloWorldBrowserSpec extends BaseSpec {


  "HelloWorldBrowserSpec" should {
    "open the hello world page" in {

      println(port)

      go to (page = HelloWorldPage)

      val src = HelloWorldPage.pageSource

      HelloWorldPage should be(displayed)

    }
  }
}
