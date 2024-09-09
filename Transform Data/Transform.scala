import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.{SparkSession, functions => F}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.IntegerType

object Transform {
  def main(args: Array[String]): Unit = {
    // Create SparkSession
    val spark = SparkSession.builder()
      .appName("Spark Session Example")
      .master("local[*]")  // Use "local[*]" for local mode, or configure according to your cluster
      .getOrCreate()

    // Import implicits for the `$` notation
    import spark.implicits._

    //Create DataFrame
    val df = spark.read
      .option("header","true")
      .csv("C:\\Users\\hp\\Downloads\\olympic_data.csv")

    //val CheckData1 = df.where($"Sex".isin("Male","Female")).show(2)
    //val CheckData2 = df.where(!$"Sex".isin("Male","Female") && $"Age".isin("Male","Female")).show(2)
    //val CheckData3 = df.where(!$"Sex".isin("Male","Female") && !$"Age".isin("Male","Female")).show(2)

    // --As you can i see my data has some issu. that is My sex column is wrong for 666 rows. and also My Age Column is wrong for 7 Rows.
    // --My Sex column value is in the Age column can you see that and After name column Every Column is like that.
    //--I have to slice Dataframe.
    //val TotalRows = df.count
    //val Df1Rows = df.where($"Sex".isin("Male","Female")).count
    //val Df2Rows = df.where(!$"Sex".isin("Male","Female") && $"Age".isin("Male","Female")).count
    //val Df3Rows = df.where(!$"Sex".isin("Male","Female") && !$"Age".isin("Male","Female")).count
    //TotalRows - (Df1Rows + Df2Rows + Df3Rows)

    //--now i have to fix it. i wanna fix it that way like i wanna delete this Sex column and i will make Age column is Sex Column, and Every Column i will Rename it.
    val DataModule2 = df.where(!$"Sex".isin("Male","Female") && $"Age".isin("Male","Female"))
    val RenameColumn1 = DataModule2
      .drop("Sex")
      .withColumnRenamed("Age","Sex")
      .withColumnRenamed("Height","Age")
      .withColumnRenamed("Weight","Height")
      .withColumnRenamed("Team","Weight")
      .withColumnRenamed("NOC","Team")
      .withColumnRenamed("Games","NOC")
      .withColumnRenamed("Year","Games")
      .withColumnRenamed("Season","Year")
      .withColumnRenamed("City","Season")
      .withColumnRenamed("Sport","City")
      .withColumnRenamed("Event","Sport")
      .withColumnRenamed("Medal","Event")
      .withColumn("Medal",lit("NA"))

    //--I don't have Event and Medal Column, that's why i have to create it.
    val DataModule3 = df.where(!$"Sex".isin("Male","Female") && !$"Age".isin("Male","Female"))
    val RenameColumn2 = DataModule3
      .drop("Sex","Age")
      .withColumnRenamed("Height","Sex")
      .withColumnRenamed("Weight","Age")
      .withColumnRenamed("Team","Height")
      .withColumnRenamed("NOC","Weight")
      .withColumnRenamed("Games","Team")
      .withColumnRenamed("Year","NOC")
      .withColumnRenamed("Season","Games")
      .withColumnRenamed("City","Year")
      .withColumnRenamed("Sport","Season")
      .withColumnRenamed("Event","City")
      .withColumnRenamed("Medal","Sport")
      .withColumn("Event",lit("NA"))
      .withColumn("Medal",lit("NA"))

    val DataModule1 = df.where($"Sex".isin("Male","Female"))
    val DataFrame1 = DataModule1.select($"ID",$"Name",$"Sex",$"Age",$"Height",$"Weight",$"Team",$"NOC",$"Games",$"Year",$"Season",$"City",$"Sport",$"Event",$"Medal")
    val DataFrame2 = RenameColumn1.select($"ID",$"Name",$"Sex",$"Age",$"Height",$"Weight",$"Team",$"NOC",$"Games",$"Year",$"Season",$"City",$"Sport",$"Event",$"Medal")
    val DataFrame3 = RenameColumn2.select($"ID",$"Name",$"Sex",$"Age",$"Height",$"Weight",$"Team",$"NOC",$"Games",$"Year",$"Season",$"City",$"Sport",$"Event",$"Medal")

    //my dataframe column is ok, Now i have to union all my 3 dataframes, and i have to clean it.
    //val CountRows = DataFrame1.count + DataFrame2.count + DataFrame3.count

    //--Union All by Slicing 3 Dataframe.
    val MainDataframe = DataFrame1.union(DataFrame2).union(DataFrame3)

    //Cleaning Dataframe.
    //--Drop Duplicate Value.
    val duplicateRows = MainDataframe.groupBy(MainDataframe.columns.map(col): _*)
      .count()

    // Function to remove symbols but keep spaces
    def removeSymbols(columnName: String) = {
      regexp_replace(col(columnName), "[^a-zA-Z0-9 ]", "")
    }

    // List of columns to clean
    val textColumns = Seq("ID","Name","Sex","Age","Height","Weight","Team", "NOC", "Games","Year","Season","City","Sport","Event","Medal")

    // Apply the removeSymbols function to each text column
    val cleanedDataframe = textColumns.foldLeft(duplicateRows) { (tempDf, columnName) =>
      tempDf.withColumn(columnName, removeSymbols(columnName))
    }

    //Describe Column.
    cleanedDataframe.describe("ID","Sex","Age","Height","Weight","Year","Season").show()

    // Update Column Value
    val updateValue = cleanedDataframe
      .withColumn("Sex",when($"Sex"==="Male","M").otherwise("F"))
      .withColumn("Age", when($"Age" === "NA", "0").otherwise($"Age"))
      .withColumn("Height", when($"Height" === "NA", "0").otherwise($"Height"))
      .withColumn("Weight", when($"Weight" === "NA", "0").otherwise($"Weight"))
      .withColumn("Weight", F.substring(F.col("Weight"),1,2))
      .withColumn("Medal", when($"Medal" === "Gold", "1").when($"Medal"==="Silver","2").when($"Medal"==="Bronze","3").otherwise("0"))
      .drop("Games","count")


    //Showing Updated Dataframe. and printSchema
    updateValue.printSchema()
    updateValue.show(2)

    //Describe Column.
    updateValue.describe("ID","Sex","Age","Height","Weight","Year","Season","Medal").show()

    // Convert columns to IntegerType
    val dfConverted = updateValue
      .withColumn("ID", col("ID").cast(IntegerType))
      .withColumn("Age", col("Age").cast(IntegerType))
      .withColumn("Height", col("Height").cast(IntegerType))
      .withColumn("Weight", col("Weight").cast(IntegerType))
      .withColumn("Year", col("Year").cast(IntegerType))
      .withColumn("Medal",col("Medal").cast(IntegerType))

    //Showing Converted dataframe.
    dfConverted.show(2)

    // Calculate the number of missing values for each column
    val missingCounts = dfConverted.columns.map { colName =>
      val countMissing = dfConverted.filter(col(colName).isNull).count()
      (colName, countMissing)
    }

    // Print the result
    missingCounts.foreach { case (colName, count) =>
      println(s"Column '$colName' has $count missing values.")
    }



    //Now I have to configure Aws Credentials.
    val accessKey = "Your Access Key"
    val secretKey = "Your Secret Key"

    // Set AWS credentials in Spark context
    spark.sparkContext.hadoopConfiguration.set("fs.s3a.access.key", accessKey)
    spark.sparkContext.hadoopConfiguration.set("fs.s3a.secret.key", secretKey)

    //write Data to s3
    // Coalesce DataFrame into 1 partition
    val singlePartitionDF = dfConverted.coalesce(1)

    // Replace with your S3 bucket and file path
    val s3Bucket = "s3a://olympic-data-of-120year/output"

    // Write DataFrame to S3 with header
    singlePartitionDF.write
      .mode("overwrite")  // Modes: overwrite, append, ignore, errorIfExists
      .format("csv")      // Format: can also be "csv", "parquet", etc.
      .option("header", "true")  // Include header in the CSV output
      .save(s3Bucket)   // Save to S3 bucket
    println("Successfully Saved Data with Header")

  }

}
