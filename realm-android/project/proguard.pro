-ignorewarnings

-keepparameternames
-keepattributes Exceptions,InnerClasses,Signature,*Annotation*,EnclosingMethod
-keep class scala.Dynamic

#-printseeds  logs/printseeds
#-printusage  logs/printusage

-keep class retrofit.** { *; }
-keepclasseswithmembers class * { @retrofit.http.* <methods>; }
-keepclasseswithmembers class * { @com.fasterxml.jackson.annotation.* <methods>; }
-keep class scala.reflect.ScalaSignature { *; }

-dontwarn retrofit.**
-dontwarn javax.**
-dontwarn com.squareup.okhttp.**

-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class * { *; }
-dontwarn javax.**
-dontwarn io.realm.**

-keep class com.android.** { *; }
-keep class android.support.test.runner.AndroidJUnitRunner
-keep public class * extends junit.framework.TestCase
-keepclassmembers class * extends junit.framework.TestCase { *; }
