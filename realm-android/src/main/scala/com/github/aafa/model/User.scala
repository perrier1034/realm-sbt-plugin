package com.github.aafa.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class User extends RealmObject{
  @PrimaryKey var id: Long = 1
  var name: String = "test"
}