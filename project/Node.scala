import sbt.{Def, _}

import scala.util.Try

/*
 * This is for testing only to check if we can work with npm / node
 */
object Node {

  lazy val npmVersion  = taskKey[Unit]("npmVersion")

  lazy val nodeVersion = taskKey[Unit]("nodeVersion")

  private val logger   = ConsoleLogger()

  lazy val tasks: Seq[Def.Setting[Task[Unit]]] = {
    Seq(
      npmVersion := {
        logger.info(s"Using npm version: ${versionOf("npm")}")
      },
      nodeVersion := {
        logger.info(s"Using node version: ${versionOf("node")}")
      }
    )
  }

  private def versionOf(cmd: String): String = {
    import sys.process._
    Try(s"$cmd --version".!!).getOrElse("not installed")
  }

}
