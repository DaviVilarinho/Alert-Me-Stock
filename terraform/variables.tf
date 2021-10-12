variable "ami_id" {
  description = "EC2 (UBUNTU) machine id that defines which one will be deployed"
  type        = string
  value       = "ami-00399ec92321828f5"
  validation {
    condition     = can(regex("^ami-", var.image_id))
    error_message = "images should start with \'ami\'"
  }
}

variable "instance_name" {
  description = "EC2 instance name"
  type        = string
  value       = "stocks_database"
}

variable "profile" {
  description = "AWS profile"
  type        = string
  value       = "default"
}

variable "region" {
  description = "AWS region"
  type        = string
  value       = "us-east-2"
}

variable "instance_type" {
  description = "EC2 instance type"
  type        = string
  value       = "t2.micro"
}
