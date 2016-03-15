package com.github.aafa.activity


import android.app.Activity
import android.os.Bundle
import android.widget.{LinearLayout, TextView}
import com.github.aafa.model.User
import io.realm.RealmConfiguration.Builder
import io.realm._
import macroid.FullDsl._
import macroid._

/**
  * Copyright (c) aafa. All rights reserved.
  * Created by aafa
  */

class MainActivity extends Activity with Contexts[Activity] with MainActivityView {

  def realmConfiguration: RealmConfiguration = new Builder(this)
    .deleteRealmIfMigrationNeeded()
    .build()

  def realm: Realm = Realm.getInstance(realmConfiguration)

  override def onCreate(b: Bundle): Unit = {
    super.onCreate(b)
    setTitle("Hello world, realm-test!")
    setContentView(ui.get)
    test()
  }

  def test(): Unit = {
    val user: User = new User()
    user.name = "Hello, Realm!"
    user.id = 1

    realm.beginTransaction()
    realm.clear(classOf[User])
    realm.copyToRealm(user)
    realm.commitTransaction()

    val realmUser: User = realm.where(classOf[User]).equalTo("id", new Integer(1)).findFirst()
    updateText(realmUser.name)
    println(realmUser.name)
  }

}

trait MainActivityView {
  this: MainActivity =>

  var textSlot = slot[TextView]

  def ui: Ui[LinearLayout] = {
    l[LinearLayout](
      w[TextView] <~ wire(textSlot)
    )
  }

  def updateText(s: String) = runUi(textSlot <~ text(s))
}


