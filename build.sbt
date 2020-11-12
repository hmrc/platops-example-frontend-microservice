import scoverage.ScoverageKeys
import uk.gov.hmrc.DefaultBuildSettings.{integrationTestSettings, oneForkedJvmPerTest}
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin.publishingSettings
import uk.gov.hmrc.versioning.SbtGitVersioning

val appName: String = "platops-example-frontend-microservice"

lazy val microservice = Project(appName, file("."))
  .enablePlugins(Seq(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtDistributablesPlugin): _*)
  .settings(
    scalaVersion                                  := "2.12.11",
    majorVersion                                  := 2,
    libraryDependencies                           ++= AppDependencies.compile ++ AppDependencies.test,
    evictionWarningOptions in update              := EvictionWarningOptions.default.withWarnScalaVersionEviction(false)
  )
  .settings(publishingSettings: _*)
  .settings(scoverageSettings: _*)
  .configs(IntegrationTest)
  .settings(integrationTestSettings(): _*)
  .settings(Node.tasks: _*)
  .settings((test in Test) := ((test in Test) dependsOn (Node.npmVersion, Node.nodeVersion)).value)
  .settings(PlayKeys.playDefaultPort := 9930)

lazy val scoverageSettings = {
  val excludedPackages = Seq(
    ".*Routes.*",
    ".*RoutesPrefix.*",
    ".*Reverse.*",
    "uk.gov.hmrc.BuildInfo.*",
    "uk.gov.hmrc.*.views.html.*error_template.*",
    "uk.gov.hmrc.*.views.html.*govuk_wrapper.*",
    "uk.gov.hmrc.*.views.html.*main_template.*",
    "com.kenshoo.play.metrics.*"
  )
  Seq(
    ScoverageKeys.coverageExcludedPackages := excludedPackages.mkString(";"),
    ScoverageKeys.coverageMinimum          := 80,
    ScoverageKeys.coverageFailOnMinimum    := false,
    ScoverageKeys.coverageHighlighting     := true
  )
}
