import play.core.PlayVersion
import sbt._

object AppDependencies {
  val bootstrapVersion = "8.1.0"

  val compile = Seq(
    "uk.gov.hmrc" %% "play-frontend-hmrc-play-30"      % "8.1.0",
    "uk.gov.hmrc" %% "bootstrap-frontend-play-30"      % bootstrapVersion,
    "uk.gov.hmrc" %% "platops-example-library-play-30" % "0.201.0"
  )

  val test = Seq(
    "uk.gov.hmrc" %% "bootstrap-test-play-30"          % bootstrapVersion % Test,
    "org.jsoup"   %  "jsoup"                           % "1.17.1"         % Test
  )

  val it = Seq.empty
}
