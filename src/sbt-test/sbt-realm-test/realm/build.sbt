import android.Keys._

android.Plugin.androidBuild

lazy val root = (project in file(".")).enablePlugins(RealmPlugin, AndroidPlugin)

proguardOptions in Android ++= Seq(
  "-ignorewarnings"
)