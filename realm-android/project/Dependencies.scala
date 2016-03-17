import android.Keys._
import sbt._
import Keys._

object Dependencies {

  lazy val resolverUrls =
    Seq(
      "jcenter" at "http://jcenter.bintray.com"
    )


  lazy val libs: Seq[sbt.Setting[_]] = Seq(
    resolvers ++= resolverUrls,

    libraryDependencies ++= Seq(
      "org.macroid" %% "macroid" % "2.0.0-M4",
      "com.github.aafa" %% "realm-scala" % "0.1.0-SNAPSHOT"
    ),

    // unit tests
    fork in Test := true,

    libraryDependencies ++= Seq(
      "com.geteit" %% "robotest" % "0.12" % "test",
      "org.scalatest" %% "scalatest" % "2.2.5" % "test"
    ),


    // instrumental tests
    debugIncludesTests in Android := true,

    libraryDependencies ++= Seq(
      "io.reactivex" % "rxjava" % "1.1.0",
      "com.android.support" % "support-annotations" % "23.0.1",
//      "com.android.support.test" % "testing-support-lib" % "0.1",
      "com.android.support.test.espresso" % "espresso-core" % "2.2.1",
      "com.android.support.test" % "runner" % "0.4.1",
      "com.android.support.test" % "rules" % "0.4.1"
    ),

    autoScalaLibrary := false,

    instrumentTestRunner in Android := "android.support.test.runner.AndroidJUnitRunner"
  )

}