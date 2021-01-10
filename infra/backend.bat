set APP_NAME=sudoku-infra

REM heroku login
REM heroku authorizations:create
heroku create --region eu %APP_NAME%
heroku addons:create heroku-postgresql:hobby-dev --app %APP_NAME%
heroku config:get DATABASE_URL --app %APP_NAME%