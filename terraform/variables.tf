variable "ami_id" {
  description = "EC2 (UBUNTU) machine id that defines which one will be deployed"
  type        = string
  default       = "ami-00399ec92321828f5"
  validation {
    condition     = can(regex("^ami-", var.image_id))
    error_message = "images should start with \'ami\'"
  }
}

variable "instance_name" {
  description = "EC2 instance name"
  type        = string
  default       = "stocks_database"
}

variable "instance_type" {
  description = "EC2 instance type"
  type        = string
  default       = "t2.micro"
}

