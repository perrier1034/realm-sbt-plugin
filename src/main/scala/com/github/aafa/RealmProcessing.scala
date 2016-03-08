package com.github.aafa

import java.util

import android.Keys._
import com.android.build.api.transform.QualifiedContent.ContentType
import com.android.build.api.transform._
import io.realm.transformer.RealmTransformer
import org.gradle.api.logging.LoggingManager
import org.gradle.logging.internal.DefaultLoggingManager
import sbt.Keys._
import sbt._
import scala.collection.JavaConverters._

import scala.language.postfixOps

object RealmProcessing {
  lazy val realmProcessAnnotations: TaskKey[Unit] = TaskKey[Unit]("realm-annotations", "realm annotations")
  lazy val realmTransformer: TaskKey[Unit] = TaskKey[Unit]("realm-transform", "realm transform")

  val tasks = Seq(
    realmProcessAnnotations <<= Def.task {
      val classDir = (classDirectory in Compile).value
      val classpath = ((products in Compile).value ++ (bootClasspath in Android).value.files ++ (dependencyClasspath in Compile).value.files) mkString ":"

      val classes: Stream[File] = getFileTree(classDir).filter(_.name.endsWith(".class"))
      val className: (File) => String = _.getAbsolutePath.replace(classDir.absolutePath + "/", "").replace(".class", "").replace("/", ".")
      val internalClass: (String) => Boolean = _.contains("$")
      val filesList: String = classes.map(className).filterNot(internalClass) mkString " "

      val command = s"javac -source 1.7 -target 1.7 -classpath $classpath -processor io.realm.processor.RealmProcessor -XprintRounds -d $classDir $filesList"
      Process(command) !

//      println("! doing command " + command)
//      println("! classes " + classes)
    },

    realmTransformer <<= Def.task {
      val transformer: RealmTransformer = new RealmTransformer()
      val classDir: File = (products in Compile).value.head

      val output = new TransformOutput(classDir)
      val classes = transformFiles(dirs = Seq(classDir))
      val references = transformFiles(files = (dependencyClasspath in Compile).value.files)

      transformer.transform(new UndefinedContext, classes, references, output, false)
    } dependsOn realmProcessAnnotations
  )

  def transformFiles(files: Seq[File] = Seq(), dirs: Seq[File] = Seq()): util.ArrayList[TransformInput] = {
    new util.ArrayList[TransformInput](util.Arrays.asList(new Jars(files, dirs)))
  }

  class Jars(files: Seq[File], dirs: Seq[File] = Seq()) extends TransformInput {
    override def getJarInputs: util.Collection[JarInput] = {
      def jar(f: File) = {
        new JarWrapper(f)
      }

      val javaCollection: util.Collection[JarWrapper] = (files map jar).asJavaCollection
      new util.ArrayList[JarInput](javaCollection)
    }

    override def getDirectoryInputs: util.Collection[DirectoryInput] = {
      def dir(f: File) = {
        val d: File = f
        println(s"got dir input $d")
        new DirectoryInputWrapper(d)
      }

      val javaCollection: util.Collection[DirectoryInputWrapper] = (dirs map dir).asJavaCollection
      new util.ArrayList(javaCollection)
    }
  }

  class JarWrapper(f: File) extends JarInput {
    override def getStatus: Status = Status.ADDED

    override def getName: String = f.getAbsolutePath

    override def getContentTypes: util.Set[ContentType] = new util.HashSet[ContentType](util.Arrays.asList(QualifiedContent.DefaultContentType.CLASSES))

    override def getScopes: util.Set[QualifiedContent.Scope] = new util.HashSet[QualifiedContent.Scope](util.Arrays.asList(QualifiedContent.Scope.PROJECT))

    override def getFile: File = f
  }

  class DirectoryInputWrapper(f: File) extends DirectoryInput {
    override def getChangedFiles: util.Map[File, Status] = {
      println("! getChangedFiles")
      val map: util.HashMap[File, Status] = new util.HashMap()
      map.put(file("/Users/aafa/Projects/sandbox/realm-test/target/android/intermediates/classes/com/github/aafa/activity/MainActivity.class"), Status.NOTCHANGED)
      map
    }

    override def getName: String = f.getName

    override def getContentTypes: util.Set[ContentType] = new util.HashSet[ContentType](util.Arrays.asList(QualifiedContent.DefaultContentType.CLASSES))

    override def getScopes: util.Set[QualifiedContent.Scope] = new util.HashSet[QualifiedContent.Scope](util.Arrays.asList(QualifiedContent.Scope.PROJECT))

    override def getFile: File = f
  }

  class TransformOutput(f: File) extends TransformOutputProvider {
    override def deleteAll(): Unit = {
      println("TransformOutput deleteAll")
    }

    override def getContentLocation(s: String, set: util.Set[ContentType], set1: util.Set[QualifiedContent.Scope], format: Format): File = {
      println(s"TransformOutput $s; format $format")
      f
    }
  }

  class UndefinedContext extends com.android.build.api.transform.Context {
    override def getTemporaryDir: File = ???

    override def getLogging: LoggingManager = ???

    override def getPath: String = ???
  }


  def getFileTree(f: File): Stream[File] =
    f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree)
    else Stream.empty)
}



