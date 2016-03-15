package com.github.aafa

import android.content.ContextWrapper
import android.os.Build.VERSION_CODES._
import macroid.{AppContext, ActivityContext}
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.scalatest.{RobolectricSuite, Matchers, FlatSpec}

/**
  * Created by Alexey Afanasev on 25.02.16.
  */
@Config(sdk = Array(LOLLIPOP))
abstract class AbstractRoboSpec extends FlatSpec with Matchers with RobolectricSuite {
  val context = RuntimeEnvironment.application
  implicit val appContext: AppContext = AppContext(context)
}
