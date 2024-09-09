import kaggle
import os

# Set the Kaggle API credentials if not set in environment variables
os.environ['KAGGLE_USERNAME'] = 'khayrulalamtouhid'
os.environ['KAGGLE_KEY'] = '96e59ae227c9e9b631a726d3647d7719'

# Example: Download a dataset
dataset = 'olistbr/brazilian-ecommerce'  # Replace with your dataset's identifier
output_path = 'D:\\My Project'    # Replace with your desired output path

# Ensure the output path exists
if not os.path.exists(output_path):
    os.makedirs(output_path)

# Download the dataset
kaggle.api.dataset_download_files(dataset, path=output_path, unzip=True)
