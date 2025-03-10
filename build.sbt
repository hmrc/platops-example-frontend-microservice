import scoverage.ScoverageKeys
import uk.gov.hmrc.DefaultBuildSettings
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin

ThisBuild / majorVersion  := 2
ThisBuild / scalaVersion  := "3.3.5"
ThisBuild / scalacOptions += "-Wconf:msg=Flag.*repeatedly:s"

lazy val microservice = Project("platops-example-frontend-microservice", file("."))
  .enablePlugins(play.sbt.PlayScala, SbtDistributablesPlugin)
  .settings(libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test)
  .settings(scoverageSettings: _*)
  .settings(Node.tasks: _*)
  .settings(Test / test := ((Test / test).dependsOn(Node.npmVersion, Node.nodeVersion)).value)
  .settings(PlayKeys.playDefaultPort := 9930)
  .settings(
    scalacOptions += "-Wconf:msg=unused import&src=html/.*:s",
    scalacOptions += "-Wconf:src=routes/.*:s"
  )

lazy val it = project
  .enablePlugins(PlayScala)
  .dependsOn(microservice % "test->test")
  .settings(DefaultBuildSettings.itSettings())
  .settings(libraryDependencies ++= AppDependencies.it)

lazy val scoverageSettings = {
  val excludedPackages = Seq(
    ".*Routes.*",
    ".*RoutesPrefix.*",
    ".*Reverse.*",
    "uk.gov.hmrc.example.config.ErrorHandler",
    "uk.gov.hmrc.example.views.html.LanguageSelect",
    "uk.gov.hmrc.example.views.html.ErrorTemplate",
    "uk.gov.hmrc.example.controllers.LanguageSwitchController",
  )
  Seq(
    ScoverageKeys.coverageExcludedPackages := excludedPackages.mkString(";"),
    ScoverageKeys.coverageMinimumStmtTotal := 80,
    ScoverageKeys.coverageFailOnMinimum    := false,
    ScoverageKeys.coverageHighlighting     := true
  )
}
