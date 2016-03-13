# SBT plugin for Realm.io 
to work on Android with https://github.com/pfn/android-sdk-plugin

###WIP

## Install 
Add sbt plugin to your build configuration (ussually `plugins.sbt`)
```
resolvers += Resolver.url("aafa-sbt", url("http://dl.bintray.com/aafa/sbt-plugins"))(Resolver.ivyStylePatterns)
addSbtPlugin("com.github.aafa" % "realm-sbt-plugin" % "0.1.0")
```

Then make sure you have enabled it for your project

```
import com.github.aafa.RealmPlugin
Project(id = "app", base = file(".")).enablePlugins(RealmPlugin)
```

To check if plugin enabled, run in sbt console  `> plugins`
```
> plugins
In file:/../
        ...
        com.github.aafa.RealmPlugin: enabled in app
```

## Usage

```
class User extends RealmObject{
  @PrimaryKey var id: Long = 1
  var name: String = "test"
}
```
