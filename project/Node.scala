import sbt.{Def, _}

// This is for testing only to check if we can work with npm / node
object Node {
  lazy val npmVersion = taskKey[Unit]("npm-version")

  lazy val nodeVersion = taskKey[Unit]("node-version")

  lazy val tasks: Seq[Def.Setting[Task[Unit]]] = {
    import sys.process._
    Seq(
      npmVersion := {
        println(s"Using npm version: ${"npm --version".!!}")
      },
      nodeVersion := {
        println(s"Using node version: ${"node --version".!!}")
      }
    )
  }
}
