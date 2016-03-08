import android.Keys._

android.Plugin.androidBuild

lazy val root = (project in file(".")).enablePlugins(RealmPlugin, AndroidPlugin)

resolvers ++= Seq(
  Resolver.mavenLocal,
  "oss" at "http://oss.jfrog.org/artifactory/oss-snapshot-local"
)

libraryDependencies ++= Seq(
  "io.realm" % "realm-android" % "0.88.0-SNAPSHOT"
)