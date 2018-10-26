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

import java.util.UUID
import java.util.concurrent.TimeUnit
import akka.actor.ActorSystem
import javax.inject.{Inject, Singleton}
import play.Logger
import play.api.Mode.Mode
import play.api.{Configuration, Environment}
import uk.gov.hmrc.http.{HeaderCarrier, HttpResponse}
import uk.gov.hmrc.play.bootstrap.http.HttpClient
import uk.gov.hmrc.play.config.ServicesConfig
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.FiniteDuration
import scala.util.{Failure, Random, Success}

@Singleton
class Scheduler @Inject()(
  actorSystem: ActorSystem,
  httpClient: HttpClient,
  override val runModeConfiguration: Configuration,
  environment: Environment)
    extends ServicesConfig {

  implicit val hc: HeaderCarrier = HeaderCarrier()

  def startScheduler(interval: FiniteDuration) = {
    Logger.info(s"Starting scheduler every $interval")
    val maxLatency = runModeConfiguration.getInt("max-latency-in-seconds").getOrElse(45)
    actorSystem.scheduler.schedule(FiniteDuration(1, TimeUnit.SECONDS), interval) {
      val i       = Random.nextInt(100)
      val latency = if (i <= 75) 0 else Random.nextInt(maxLatency) + 1
      val id      = UUID.randomUUID()
      Logger.info(s"Starting a call with latency of $latency, id=$id")
      httpClient
        .GET[HttpResponse](s"${baseUrl("platops-example-private-backend-microservice")}/example/hello-world/$latency")
        .onComplete {
          case Success(response)  => Logger.info(s"Got a response for call id=$id")
          case Failure(exception) => Logger.info(s"Failure getting response for call id=$id")
        }
    }
  }

  override protected def mode: Mode = environment.mode

}
