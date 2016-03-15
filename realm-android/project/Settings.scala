object Settings {

  lazy val apkExcludeStrings: Seq[String] = Seq(
    "META-INF/LICENSE.txt",
    "META-INF/LICENSE",
    "META-INF/NOTICE.txt",
    "META-INF/NOTICE",
    ".readme",
    "reference.conf",
    "LICENSE.txt",
    "META-INF/maven/com.squareup/javawriter/pom.xml",
    "META-INF/maven/com.squareup/javawriter/pom.properties"
  )

  lazy val proguardDebug = Seq(
    "-dontobfuscate",
    "-dontoptimize",
    "-dontpreverify"
  )

  lazy val proguardRelease = Seq(
    "-optimizations class/*,field/*,method/*,code/*",
    "-mergeinterfacesaggressively",
    "-optimizationpasses 3"
  )


  lazy val proguardCache = Seq(
  )
}
