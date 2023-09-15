import scoverage.ScoverageKeys
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin

val commonSettings = Seq(
  majorVersion := 2,
  scalaVersion := "2.13.8"
)

lazy val microservice = Project("platops-example-frontend-microservice", file("."))
  .enablePlugins(play.sbt.PlayScala, SbtDistributablesPlugin)
  .settings(commonSettings)
  .settings(libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test)
  .settings(scoverageSettings: _*)
  .settings(Node.tasks: _*)
  .settings(Test / test := ((Test / test) dependsOn (Node.npmVersion, Node.nodeVersion)).value)
  .settings(PlayKeys.playDefaultPort := 9930)
  .settings(
    scalacOptions += "-Wconf:cat=unused-imports&src=html/.*:s",
    scalacOptions += "-Wconf:src=routes/.*:s",
  )

lazy val it =
  (project in file("it"))
    .enablePlugins(PlayScala)
    .settings(commonSettings)
    .settings(libraryDependencies ++= AppDependencies.it)
    .dependsOn(microservice % "test->test")

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
