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
