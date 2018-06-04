import sbt._

object Node {
  lazy val npmInstall = taskKey[Unit]("npm-install")

  lazy val tasks = Seq(
    npmInstall := {
      import sys.process._
      val result = "npm install".!!
      println(result)
    }
  )
}
