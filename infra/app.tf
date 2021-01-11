resource "heroku_app" "main" {
  name   = var.app_name
  region = var.region
  stack  = var.stack
}