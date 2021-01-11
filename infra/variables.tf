variable "email" {
    description = "The email address associated with the account"
}

variable "api_key" {
    description = "The key used to access the heroku API"
}

variable "app_name" {
    description = "The name of the application"
}

variable "region" {
    description = "The region in which the application is deployed"
    default = "eu"
}

variable "stack" {
    description = "The stack used to deploy the application"
    default = "container"
}