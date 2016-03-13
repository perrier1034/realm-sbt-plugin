package com.github.aafa

import sbt.Keys._
import sbt._


object RealmPlugin extends AutoPlugin {
  override def trigger = allRequirements
  override def requires = android.AndroidPlugin

  override def projectSettings: Seq[Setting[_]] = RealmProcessing.tasks ++ realmSettings

  val realmSettings = Seq(
    (packageBin in Compile) <<= (packageBin in Compile) dependsOn RealmProcessing.realmTransformer
  )
}