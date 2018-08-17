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

package uk.gov.hmrc.example.spec

import java.net.URL

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.{DesiredCapabilities, RemoteWebDriver}
import org.openqa.selenium.support.ui.WebDriverWait
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.selenium.WebBrowser
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder

import scala.util.{Properties, Try}

trait BaseSpec extends WordSpec
  with GivenWhenThen
  with Matchers
  with WebBrowser
  with BeforeAndAfterAll
  with BeforeAndAfterEach
  with ScalaFutures
  with PageMatchers
  with GuiceOneServerPerSuite {

  override lazy val port: Int = 6001
  implicit override lazy val app: Application =  GuiceApplicationBuilder().build()

  implicit lazy val webDriver: WebDriver = {

    val driver = Properties.propOrElse("browser", "chrome") match {
      case "firefox" =>
        new FirefoxDriver()
      case "chrome" =>
        new ChromeDriver()
      case "remote-chrome" =>
        new RemoteWebDriver(new URL(s"http://localhost:4444/wd/hub"), DesiredCapabilities.chrome)
      case "remote-firefox" =>
        new RemoteWebDriver(new URL(s"http://localhost:4444/wd/hub"), DesiredCapabilities.firefox)
      case other => fail(s"Browser '$other' not recognised")
    }
    setCaptureDir("./target/screenshots/")
    driver
  }

  lazy val webDriverWait = new WebDriverWait(webDriver, 10)

  override def beforeAll() {
    super.beforeAll()
    sys.addShutdownHook {
      Try(webDriver.quit())
    }
  }

  override def afterAll() {
    Try(webDriver.quit())
  }

  override def withFixture(test: NoArgTest): Outcome = {
    var fixture = super.withFixture(test)
    if (!fixture.isSucceeded) capture to test.name
    fixture
  }
}