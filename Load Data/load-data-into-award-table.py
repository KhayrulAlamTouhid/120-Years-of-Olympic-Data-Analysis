import boto3
import pandas as pd
import mysql.connector
from io import StringIO
import os

# Configuration Aws RDS mySql
s3_bucket_name = 'olympic-data-of-120year'
s3_file_key = 'output/part-00000-tid-96446017459725312-eb952688-7e37-45a1-b88a-f91ed17ba840-904-1-c000.csv'
rds_host = 'database-1.cdw6i20agl9g.ap-southeast-1.rds.amazonaws.com'
rds_user = 'admin'
rds_password = '1310380131'
database_name = 'Olympic_Database'

# Set AWS credentials (if needed)
os.environ['AWS_ACCESS_KEY_ID'] = 'aws access key'
os.environ['AWS_SECRET_ACCESS_KEY'] = 'aws secret key'

def download_s3_file(bucket_name, file_key):
    """Download file from S3 and return its content."""
    s3_client = boto3.client('s3')
    try:
        s3_object = s3_client.get_object(Bucket=bucket_name, Key=file_key)
        file_content = s3_object['Body'].read().decode('utf-8')
        return file_content
    except Exception as e:
        print(f"Error downloading file from S3: {e}")
        return None

def load_data_to_dataframe(file_content):
    """Load CSV data into a Pandas DataFrame."""
    try:
        data = pd.read_csv(StringIO(file_content))
        print("Data loaded into DataFrame successfully!")
        return data
    except Exception as e:
        print(f"Error loading data into DataFrame: {e}")
        return None

def create_database_and_table():
    """Create database and table if they do not exist."""
    try:
        # Connect to MySQL server
        connection = mysql.connector.connect(
            host=rds_host,
            user=rds_user,
            password=rds_password
        )
        cursor = connection.cursor()

        # Create database
        cursor.execute(f"CREATE DATABASE IF NOT EXISTS `{database_name}`;")
        print(f"Database '{database_name}' created successfully!")

        # Select the database
        cursor.execute(f"USE `{database_name}`;")

        # Create Award table
            create_award_table_sql = """
            CREATE TABLE IF NOT EXISTS Award (
                award_number INT,
                award_name VARCHAR(8)
            );
            """
        cursor.execute(create_table_sql)
        print("Table 'Athlete' created successfully!")

    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        if 'cursor' in locals():
            cursor.close()
        if 'connection' in locals():
            connection.close()

def insert_award_data():
    """Insert data into the Award table."""
    try:
        with mysql.connector.connect(
            host=rds_host,
            user=rds_user,
            password=rds_password,
            database=database_name
        ) as connection:
            cursor = connection.cursor()

            # Prepare data for insert into Award table
            award_data = [
                (0, 'nothing'),
                (1, 'Gold'),
                (2, 'Silver'),
                (3, 'Bronze')
            ]
            sql = """
            INSERT INTO Award (award_number, award_name) 
            VALUES (%s, %s)
            """
            cursor.executemany(sql, award_data)
            connection.commit()
            print("Data loaded into 'Award' table successfully!")

    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        if 'cursor' in locals():
            cursor.close()
        if 'connection' in locals():
            connection.close()

# Main execution
file_content = download_s3_file(s3_bucket_name, s3_file_key)
if file_content:
    data = load_data_to_dataframe(file_content)
    if data is not None:
        create_database_and_table()
        insert_data_into_table(data)
