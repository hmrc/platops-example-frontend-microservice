package uk.gov.hmrc.example

import org.scalatest.{Matchers, WordSpec}
import play.api.libs.ws.WSClient
import uk.gov.hmrc.integration.ServiceSpec

class ExampleIntegrationTest extends WordSpec with Matchers with ServiceSpec  {

  def externalServices: Seq[String] = Seq("datastream")

  override def additionalConfig: Map[String, _] = Map("auditing.consumer.baseUri.port" -> externalServicePorts("datastream"))


  "This integration test" should {
    "start datastream via smserver" in {

      val wsClient = app.injector.instanceOf[WSClient]

      val response = wsClient.url(resource("/platops-example-frontend-microservice/hello-world")).get.futureValue
      response.status shouldBe 200

    }
  }
}
