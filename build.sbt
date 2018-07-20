import sbt.Tests.{Group, SubProcess}
import scoverage.ScoverageKeys
import uk.gov.hmrc.DefaultBuildSettings.addTestReportOption
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
    routesGenerator                               := StaticRoutesGenerator
  )
  .settings(publishingSettings: _*)
  .settings(scoverageSettings: _*)
  .configs(IntegrationTest)
  .settings(inConfig(IntegrationTest)(Defaults.itSettings): _*)
  .settings(
    Keys.fork in IntegrationTest                  := false,
    testGrouping in IntegrationTest               := oneForkedJvmPerTest((definedTests in IntegrationTest).value),
    parallelExecution in IntegrationTest          := false,
    unmanagedSourceDirectories in IntegrationTest := (baseDirectory in IntegrationTest)(base => Seq(base / "it")).value,
    addTestReportOption(IntegrationTest, "int-test-reports")
  )
  .settings(Node.tasks: _*)
  .settings((test in Test) := ((test in Test) dependsOn (Node.npmVersion, Node.nodeVersion)).value)


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

def oneForkedJvmPerTest(tests: Seq[TestDefinition]) =
    tests map {
        test => Group(test.name, Seq(test), SubProcess(ForkOptions(runJVMOptions = Seq("-Dtest.name=" + test.name))))
    }
