terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.27"
    }
  }

  required_version = ">= 0.14.9"
} 

provider "aws" {
  profile = "default"
  region  = "us-east-2"
}

resource "aws_db_psql" "db_stocks" {
  allocated_storage     = 1  
  max_allocated_storage = 3
# todo: decide NoSQL -> cheaper
  engine                = ""
  engine_version        = ""
}
