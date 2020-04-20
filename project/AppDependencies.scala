import play.core.PlayVersion
import sbt._

object AppDependencies {
  val compile = Seq(
    "uk.gov.hmrc"       %% "govuk-template"    % "5.54.0-play-26",
    "uk.gov.hmrc"       %% "play-ui"           % "8.8.0-play-26",
    "uk.gov.hmrc"       %% "bootstrap-play-26" % "1.7.0"
  )

  val test = Seq(
    "uk.gov.hmrc"             %% "bootstrap-play-26"        % "1.7.0"             % Test classifier "tests",
    "org.scalatest"           %% "scalatest"                % "3.0.8"             % Test,
    "org.scalatestplus.play"  %% "scalatestplus-play"       % "3.1.2"             % "test, it",
    "org.pegdown"             % "pegdown"                   % "1.6.0"             % "test, it",
    "com.typesafe.play"       %% "play-test"                % PlayVersion.current % Test,
    "org.jsoup"               % "jsoup"                     % "1.10.2"            % Test,
    "org.seleniumhq.selenium" % "selenium-java"             % "2.53.1"            % Test,
    "org.seleniumhq.selenium" % "selenium-htmlunit-driver"  % "2.52.0"            % Test,
    "uk.gov.hmrc"             %% "service-integration-test" % "0.10.0-play-26"    % "test, it"
  )
}
