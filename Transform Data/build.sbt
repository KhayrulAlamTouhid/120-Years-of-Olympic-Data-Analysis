name := "Transform_Data"
version := "1.0"
scalaVersion := "2.12.15"

// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.1"
// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.3.2"

// AWS S3 dependencies
libraryDependencies += "org.apache.hadoop" % "hadoop-aws" % "3.3.4"  // Ensure compatibility with your Hadoop and Spark versions
libraryDependencies += "com.amazonaws" % "aws-java-sdk-s3" % "1.12.348" // Replace with the appropriate version