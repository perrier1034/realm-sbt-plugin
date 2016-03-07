package com.github.aafa

import sbt.Keys._
import sbt._


object RealmPlugin extends AutoPlugin {

  /**
   * Defines all settings/tasks that get automatically imported,
   * when the plugin is enabled
   */
  object autoImport {
    lazy val settings = Seq(
      (packageBin in Compile) <<= (packageBin in Compile) dependsOn RealmProcessing.realmTransformer
    ) ++ RealmProcessing.tasks
  }

  import autoImport._

  /**
   * Provide default settings
   */
  override def projectSettings: Seq[Setting[_]] = settings


}