import play.core.PlayVersion
import sbt._

object AppDependencies {
  val compile = Seq(
    "uk.gov.hmrc"       %% "play-frontend-hmrc"         % "0.51.0-play-28",
    "uk.gov.hmrc"       %% "bootstrap-frontend-play-28" % "4.1.0",
    "uk.gov.hmrc"       %% "play-language"              % "4.11.0-play-28",
    "uk.gov.hmrc"       %% "platops-example-library"    % "0.127.0-play-28"
  )

  val test = Seq(
    "uk.gov.hmrc"             %% "bootstrap-test-play-27"             % "4.1.0"             % Test,
    "com.typesafe.play"       %% "play-test"                          % PlayVersion.current % Test,
    "org.jsoup"               %  "jsoup"                              % "1.13.1"            % Test,
    "org.scalatestplus.play"  %% "scalatestplus-play"                 % "5.1.0"             % "test, it",
    "com.vladsch.flexmark"    %  "flexmark-all"                       % "0.35.10"           % "test, it",
    "uk.gov.hmrc"             %% "service-integration-test"           % "1.1.0-play-28"     % "test, it"
  )
}
