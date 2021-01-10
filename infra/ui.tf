resource "heroku_app" "ui" {
  name   = "${var.appname}-ui"
  region = var.region
  stack  = var.stack
}