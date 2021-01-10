resource "heroku_app" "main" {
  name   = var.appname
  region = var.region
  stack  = var.stack
}