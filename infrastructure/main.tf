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


resource "aws_db_instance" "mysql_money_database" {
  allocated_storage      = 10
  engine                 = "mysql"
  instance_class         = "db.t2.micro"
  name                   = "money"
  username               = var.db_user
  password               = var.db_password
  skip_final_snapshot    = true
}