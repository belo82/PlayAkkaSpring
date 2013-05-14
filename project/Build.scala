import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "Todolist"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
      "org.springframework" % "spring-context" % "3.2.2.RELEASE"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here
      resolvers += "uk.maven.org" at "http://uk.maven.org/maven2"
    )

}
