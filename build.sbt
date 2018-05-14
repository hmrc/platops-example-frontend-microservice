import play.core.PlayVersion
import sbt.Tests.{Group, SubProcess}
import uk.gov.hmrc.DefaultBuildSettings.{addTestReportOption, defaultSettings, scalaSettings}
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin.publishingSettings
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.SbtArtifactory
import uk.gov.hmrc.versioning.SbtGitVersioning.majorVersion

val appName: String = "platops-example-frontend-microservice"

lazy val microservice = Project(appName, file("."))
  .enablePlugins(Seq(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtDistributablesPlugin, SbtArtifactory): _*)
  .settings(scalaSettings: _*)
  .settings(publishingSettings: _*)
  .settings(defaultSettings(): _*)
  .configs(IntegrationTest)
  .settings(inConfig(IntegrationTest)(Defaults.itSettings): _*)
  .settings(
    majorVersion                                  := 2,
    scalaVersion                                  := "2.11.11",
    libraryDependencies                           ++= compile ++ test,
    retrieveManaged                               := true,
    evictionWarningOptions in update              := EvictionWarningOptions.default.withWarnScalaVersionEviction(false),
    routesGenerator                               := StaticRoutesGenerator,
    Keys.fork in IntegrationTest                  := false,
    testGrouping in IntegrationTest               := oneForkedJvmPerTest((definedTests in IntegrationTest).value),
    parallelExecution in IntegrationTest          := false,
    resolvers                                     += Resolver.bintrayRepo("hmrc", "releases"),
    resolvers                                     += Resolver.jcenterRepo,
    unmanagedSourceDirectories in IntegrationTest := (baseDirectory in IntegrationTest)(base => Seq(base / "it")).value,
    addTestReportOption(IntegrationTest, "int-test-reports")
  )


val compile = Seq(
    "uk.gov.hmrc" %% "govuk-template"     % "5.20.0",
    "uk.gov.hmrc" %% "play-ui"            % "7.14.0",
    "uk.gov.hmrc" %% "bootstrap-play-25"  % "1.5.0"
)

val test  = Seq(
    "org.scalatest"          %% "scalatest"          % "3.0.0"              % "test",
    "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0"              % "test",
    "org.pegdown"            %  "pegdown"            % "1.6.0"              % "test",
    "org.jsoup"              %  "jsoup"              % "1.10.2"             % "test",
    "com.typesafe.play"      %% "play-test"          % PlayVersion.current  % "test"
)


def oneForkedJvmPerTest(tests: Seq[TestDefinition]) =
    tests map {
        test => Group(test.name, Seq(test), SubProcess(ForkOptions(runJVMOptions = Seq("-Dtest.name=" + test.name))))
    }
