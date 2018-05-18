resolvers += Resolver.url("hmrc-sbt-plugin-releases", url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("hmrc-sbt-plugin-release-candidates", url("https://dl.bintray.com/hmrc/sbt-plugin-release-candidates"))(Resolver.ivyStylePatterns)

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += Resolver.jcenterRepo

resolvers += DefaultMavenRepository

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.12")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")

addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "1.8.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-artifactory" % "0.2.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-distributables" % "1.0.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-git-versioning" % "1.3.0")