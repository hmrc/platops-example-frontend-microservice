import play.core.PlayVersion
import sbt._

object AppDependencies {
  val compile = Seq(
    "uk.gov.hmrc"       %% "govuk-template"             % "5.59.0-play-27",
    "uk.gov.hmrc"       %% "play-ui"                    % "8.14.0-play-27",
    "uk.gov.hmrc"       %% "bootstrap-frontend-play-27" % "3.0.0",
    "uk.gov.hmrc"       %% "platops-example-library"    % "0.126.0-play-27"
  )

  val test = Seq(
    "org.scalatest"           %% "scalatest"                          % "3.1.2"             % "test, it",
    "org.scalatestplus.play"  %% "scalatestplus-play"                 % "4.0.0"             % "test, it",
    "com.vladsch.flexmark"    % "flexmark-all"                        % "0.35.10"           % "test, it",
    "com.typesafe.play"       %% "play-test"                          % PlayVersion.current % "test, it",
    "org.jsoup"               % "jsoup"                               % "1.10.2"            % "test, it",
    "uk.gov.hmrc"             %% "service-integration-test"           % "0.12.0-play-27"    % "test, it"
  )
}
