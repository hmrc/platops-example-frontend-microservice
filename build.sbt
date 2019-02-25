import scoverage.ScoverageKeys
import uk.gov.hmrc.DefaultBuildSettings.{addTestReportOption, integrationTestSettings, oneForkedJvmPerTest}
import uk.gov.hmrc.SbtArtifactory
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin.publishingSettings
import uk.gov.hmrc.versioning.SbtGitVersioning

val appName: String = "platops-example-frontend-microservice"

lazy val microservice = Project(appName, file("."))
  .enablePlugins(Seq(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtDistributablesPlugin, SbtArtifactory): _*)
  .settings(
    majorVersion                                  := 2,
    libraryDependencies                           ++= AppDependencies.compile ++ AppDependencies.test,
    evictionWarningOptions in update              := EvictionWarningOptions.default.withWarnScalaVersionEviction(false),
    routesGenerator                               := StaticRoutesGenerator,
    dependencyOverrides += "com.typesafe.play" % "play_2.11" % "2.5.12"
  )
  .settings(publishingSettings: _*)
  .settings(scoverageSettings: _*)
  .configs(IntegrationTest)
  .settings(integrationTestSettings(): _*)
  .settings(Node.tasks: _*)
  .settings((test in Test) := ((test in Test) dependsOn (Node.npmVersion, Node.nodeVersion)).value)
  .configs(AcceptanceTest)
  .settings(inConfig(AcceptanceTest)(Defaults.testSettings): _*)
  .settings(
    unmanagedSourceDirectories in AcceptanceTest := Seq((baseDirectory in AcceptanceTest).value / "acceptance"),
    unmanagedResourceDirectories in AcceptanceTest := Seq((baseDirectory in AcceptanceTest).value / "acceptance",
      (baseDirectory in AcceptanceTest).value / "target/web/public/test"),
    Keys.fork in AcceptanceTest := false,
    parallelExecution in AcceptanceTest := false,
    addTestReportOption(AcceptanceTest, "acceptance-test-reports")
  )

lazy val AcceptanceTest = config("acceptance") extend Test

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
