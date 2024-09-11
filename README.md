# 120-Years-of-Olympic-Data-Analysis
## Introduction
The Olympic Games, a global celebration of athletic prowess, have evolved significantly over the past 120 years. This project delves into a comprehensive analysis of Olympic data, uncovering trends, patterns, and insights from over a century of competition. By examining historical results, athlete performances, and country-specific achievements, we aim to provide a deeper understanding of how the Games have changed and what drives success. Our analysis not only highlights shifts in sports and athletes' strategies but also reveals broader socio-economic and cultural influences on the Olympics. This project seeks to offer valuable perspectives on the enduring legacy and future trajectory of this prestigious event.

## Architecture or Structure
### Project Architecture Details and Image.
I am working on a project analyzing 120 years of Olympic data. First, I extract data from Kaggle to local computer using Python. Then, I use Apache Spark to transform this data and save the results to AWS S3. Next, I load the transformed data from S3 into AWS RDS with Python. Finally, I visualize the data using Power BI to gain insights into Olympic trends and statistics. i have done it using intelij also I did it using Databricks. and Sometimes ChatGpt helped me.
![Project Architecture](Project%20Structure.jpg)

## Technology Used
  * Programming Languages:- Python,Scala
  * Data Processing Framework:- Apache Spark, Databricks
  * Cloud Platfrom:-
      1. Aws S3
      2. Aws RDS
  * Data Visualization:- Power Bi
  * Integrated Development Environment (IDE): Intelij
  * Support Tools: ChatGpt and Google

## Datasheet Used
The datasheet includes detailed records of athletes, with columns for their unique ID, Name, Sex, Age, Height, and Weight. It also captures their Team, NOC (National Olympic Committee), and details about the Games, including Year, Season, City, and the specific Sport and Event they participated in. The Medal column indicates the medal won or if no medal was awarded.

### More info about the data
Here is the datasheet used in this project:-https://github.com/KhayrulAlamTouhid/120-Years-of-Olympic-Data-Analysis/blob/master/Raw%20Data/athletes.csv

Orginal Datasheet i have used:- https://www.kaggle.com/datasets/heesoo37/120-years-of-olympic-history-athletes-and-results

## Scripts for The Project
### Using Intelij IDE
  * Extract Data:- [Extract Data from Kaggle](Extract%20Data/download-data.py)
  * Transform Data:- [Transform Data and save it to aws s3](Transform%20Data/Transform.scala)
  * Load Data:- [Load athlete Data into aws RDS](Load%20Data/load-data-into-athlete-table.py) and [Load award data into aws RDS](Load%20Data/load-data-into-award-table.py)
  * Visualization:- [Data Visualization](Data%20Visualization.pbix)
### Using Databricks
  * Extract Data:- [Extract Data from kaggle](Databricks/Download_Data_From_Kaggle.dbc)
  * Transform Data:- [Transform Data and Save it to aws s3](Databricks/Transform-data-and-save-it-to-s3.dbc)
  * Load Data:- [Load Data into aws RDS](Databricks/load-data-s3-to-aws-rds.dbc)

## Data Visualization
Power BI was utilized to create interactive and insightful visualizations of the athlete data. Using Power BI’s advanced features, I developed dynamic dashboards and reports that effectively showcase performance metrics, trends, and comparisons across different sports, events, and demographics. The visualizations included charts, graphs, and Map to facilitate intuitive exploration and analysis of the dataset.
![Project Report](Data%20Visualization.JPG)

## How have i made this project
### 1. Data Extraction:
Technology Used: Python   
Source: Kaggle Olympic Dataset
#### Process:
Utilized Python to access the Kaggle API and download the dataset, which includes historical data on Olympic Games spanning 120 years. I wrote a simple python script for downlad this data. 
### 2. Data Transformation:
Technology Used: Apache Spark with Scala   
Environment: Apache Spark (Scala)
#### Process:
Loaded the extracted data into Apache Spark for scalable processing.
Performed various data transformation tasks such as data cleaning, normalization, and enrichment. 
This included handling missing values, converting data types, and aggregating data to prepare it for analysis.
Used Spark DataFrames and Spark SQL to execute complex transformations efficiently on large datasets. after transformation i save it into aws s3.
### 4. Data Loading:
Technology Used: Python and AWS RDS   
Target Database: AWS RDS Mysql (Relational Database Service)
#### Process:
Used Python to interact with AWS RDS for loading the processed data into a relational database for further querying and analysis. i wrote two python script file for load the data into aws rds. That scripts created a database and made a Mysql table into aws rds and also load this data from aws s3. and another script file made a database and create a mysql table into aws rds and also load some specific data.
### 5. Analysis and Insights:
Technology Used: Power Bi  
Connect to the AWS RDS database to create interactive dashboards and reports.
Use Power BI to visualize trends, patterns, and key performance indicators related to Olympic data.
### 6. Other Tools:
* **Intelij IDE:** I have used intelij ide for write project script.
* **Databricks:** I have also used Databricks for made this project, Databricks used is easy than intelij cause Intelij IDE we have to install some libraries, scala, java, python, hadoop and some dependencies but if i use Databricks i don't have to install that much libraries, programming languages and tools.
* **Helps:** when i faced some issue and error, i took help from google and ChatGpt, They helped for writing scripts and making this project.
### Problem Description
#### 1. Background
In the "120 Years of Olympic Data Analysis" project, I have successfully extracted and transformed Olympic data and stored it in Amazon S3. The next step involves loading this data into AWS RDS for analysis. However, I'm encountering an issue related to NaN values in the data.

#### 2. Problem Statement
When attempting to load the transformed data into AWS RDS, the process fails due to the presence of NaN values in the dataset. This issue is causing the data import to either fail entirely or result in incorrect entries in the database.

#### 3. Error Messages
**Error Message:** ***"Error: Column contains NaN values which cannot be inserted into the database."***
#### 4. Steps Taken
Detail the steps I’ve already taken to resolve the issue.
 * When i faced this issue i check my aws credentials and mysql port
 * After that i use try catch functions than i saw that My database and table are successfully created but the problem when i wanna load.
 * Than I go to my spark dataframe and i checkd mydatasheet has 'nan' values, i can see my datasheet 6 field are nan values, than i fix it using scala spark  and i again save this datasheet into aws s3, That's way i fixed my issue. if you wanna see how can i fix it you can go to my github Transform Data folder.
### Summary:
This project involves a robust ETL pipeline to handle a large-scale dataset efficiently. By leveraging Python for extraction and loading, Scala with Apache Spark for transformation, and AWS S3 and RDS for storage, the project aims to create a comprehensive and accessible dataset for detailed Olympic data analysis. The end goal is to provide actionable insights and support further research or reporting on Olympic history and trends. It was my first project in my life, Sometimes i faced some issue but i have used google and chatgpt for solve this problem. This journey was so impressive. i liked this journey. Thank you Everybody.
