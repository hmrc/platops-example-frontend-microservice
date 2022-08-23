import scoverage.ScoverageKeys
import uk.gov.hmrc.DefaultBuildSettings
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin

val silencerVersion = "1.7.7"

lazy val microservice = Project("platops-example-frontend-microservice", file("."))
  .enablePlugins(play.sbt.PlayScala, SbtDistributablesPlugin)
  .settings(
    scalaVersion        := "2.12.15",
    majorVersion        := 2,
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test
  )
  .settings(SbtDistributablesPlugin.publishingSettings: _*)
  .settings(scoverageSettings: _*)
  .configs(IntegrationTest)
  .settings(DefaultBuildSettings.integrationTestSettings(): _*)
  .settings(Node.tasks: _*)
  .settings(Test / test := ((Test / test) dependsOn (Node.npmVersion, Node.nodeVersion)).value)
  .settings(PlayKeys.playDefaultPort := 9930)
  .settings(
    logBuffered := false,
    // Use the silencer plugin to suppress warnings
    // You may turn it on for `views` too to suppress warnings from unused imports in compiled twirl templates, but this will hide other warnings.
    scalacOptions += "-P:silencer:pathFilters=routes;views",
    libraryDependencies ++= Seq(
      compilerPlugin("com.github.ghik" % "silencer-plugin" % silencerVersion cross CrossVersion.full),
      "com.github.ghik" % "silencer-lib" % silencerVersion % Provided cross CrossVersion.full
    )
  )

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
    ScoverageKeys.coverageMinimum          := 80,
    ScoverageKeys.coverageFailOnMinimum    := false,
    ScoverageKeys.coverageHighlighting     := true
  )
}
