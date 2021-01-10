terraform {
  required_providers {
    heroku = {
      source  = "heroku/heroku"
      version = "3.2.0"
    }
  }
  backend "pg" {
  }
}

provider "heroku" {
  email   = var.email
  api_key = var.api_key
}
