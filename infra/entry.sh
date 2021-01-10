#!/bin/sh -l

DATABASE_URL=$1
EMAIL=$2
API_KEY=$3

terraform init -backend-config="conn_str=${DATABASE_URL}"
terraform apply -auto-approve -var="api_key=${API_KEY}"
exit 0