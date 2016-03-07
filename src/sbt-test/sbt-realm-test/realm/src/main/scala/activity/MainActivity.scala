package com.github.aafa.activity


import com.github.aafa.api.UiService
import android.content.{Intent, Context}
import android.view.View
import android.widget.{TextView, ProgressBar, LinearLayout}
import com.github.aafa.model.User
import io.realm._
import io.realm.RealmConfiguration.Builder
import android.app.Activity
import android.os.Bundle

/**
  * Copyright (c) aafa. All rights reserved.
  * Created by aafa
  */

class MainActivity extends Activity with MainActivityView {

  def realmConfiguration: RealmConfiguration = new Builder(this)
    .deleteRealmIfMigrationNeeded()
    .build()

  def realm: Realm = Realm.getInstance(realmConfiguration)

  override def onCreate(b: Bundle): Unit = {
    super.onCreate(b)
    setTitle("Hello world, realm-test!")
    test()
  }

  def test(): Unit = {
    val user: User = new User()
    user.name = "test"
    user.id = 2

    realm.beginTransaction()
    realm.clear(classOf[User])
    realm.copyToRealm(user)
    realm.commitTransaction()

    val realmUser: User = realm.where(classOf[User]).equalTo("id", new Integer(2)).findFirst()
    updateText(realmUser.name)
    println(realmUser.name)
  }

}


