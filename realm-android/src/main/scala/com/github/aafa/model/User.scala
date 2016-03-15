package com.github.aafa.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class User() extends RealmObject{
  @PrimaryKey var id: Long = 1
  var name: String = "test"
  var email: Email = null
  var role: Role = null
}

class Email extends RealmObject{
  var value: String = ""
}

class Role extends RealmObject{
  var name: String = "guest"
}