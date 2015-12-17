name := "HiveSQL"
version := "1.0"
scalaVersion := "2.10.4"
//organization := "datastax.com"
val sparkVersion = "1.4.1"
val sparkCassandraVersion = "1.4.0"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-hive" % sparkVersion % "provided"
libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % sparkCassandraVersion % "provided"

// Do not include Scala in the JAR
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

// Java Dependencies
//libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.9" // Placed in the lib directory

// META-INF discarding
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

//mainClass in assembly := Some("com.datastax.robotoil.HiveExample")

lazy val dse_run = taskKey[Unit]("Builds the package and ships it to the DSE cluster")
dse_run := {
  ("dse spark-submit " + assembly.value) !
}

    