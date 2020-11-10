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
