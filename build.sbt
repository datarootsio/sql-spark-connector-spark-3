name := "spark-mssql-connector"

organization := "com.microsoft.sqlserver.jdbc.spark"

version := "1.0.0"

crossScalaVersions := Seq("2.12.10", "2.11.12")
scalaVersion := crossScalaVersions.value.head

val sparkVersion = sys.props.get("spark.version").getOrElse("3.0.1")
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  // Spark Testing Utilities
  "org.apache.spark" %% "spark-core" % sparkVersion % "test" classifier
    "tests",
  "org.apache.spark" %% "spark-sql" % sparkVersion% "test" classifier
    "tests",
  "org.apache.spark" %% "spark-catalyst" % sparkVersion % "test" classifier
    "tests",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",

  //SQLServer JDBC jars
  "com.microsoft.sqlserver" % "mssql-jdbc" % "8.2.1.jre8"
)

scalacOptions := Seq("-unchecked", "-deprecation", "evicted")

// Exclude scala-library from this fat jar. The scala library is already there in spark package.
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)