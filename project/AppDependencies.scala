import play.core.PlayVersion
import sbt._

object AppDependencies {
  val bootstrapVersion = "7.15.0"

  val compile = Seq(
    "uk.gov.hmrc"       %% "play-frontend-hmrc"              % "7.3.0-play-28",
    "uk.gov.hmrc"       %% "bootstrap-frontend-play-28"      % bootstrapVersion,
    "uk.gov.hmrc"       %% "platops-example-library-play-28" % "0.196.0"
  )

  val test = Seq(
    "uk.gov.hmrc"      %% "bootstrap-test-play-28"           % bootstrapVersion    % Test,
    "org.jsoup"        %  "jsoup"                            % "1.13.1"            % Test
  )

  val it = Seq(
    "uk.gov.hmrc"      %% "webdriver-factory"                % "0.37.0"            % Test // Expect Bobby error
  )
}
