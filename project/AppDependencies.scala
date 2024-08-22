import play.core.PlayVersion
import sbt._

object AppDependencies {
  val bootstrapFrontendPlay = "9.3.0"

  val compile = Seq(
    "uk.gov.hmrc" %% "bootstrap-frontend-play-30"      % bootstrapFrontendPlay,
    "uk.gov.hmrc" %% "play-frontend-hmrc-play-30"      % "10.9.0",
    "uk.gov.hmrc" %% "platops-example-library-play-30" % "1.0.0"
  )

  val test = Seq(
    "uk.gov.hmrc" %% "bootstrap-test-play-30"          % bootstrapFrontendPlay  % Test,
    "org.jsoup"   %  "jsoup"                           % "1.18.1"               % Test
  )

  val it = Seq.empty
}
