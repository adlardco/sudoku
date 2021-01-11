FROM alpine:3.12.1
ENV TERRAFORM_VERSION=0.14.3
RUN apk update && apk add wget
RUN wget https://releases.hashicorp.com/terraform/${TERRAFORM_VERSION}/terraform_${TERRAFORM_VERSION}_linux_amd64.zip && \
    unzip terraform_${TERRAFORM_VERSION}_linux_amd64.zip -d /usr/bin
ENV APP_HOME=/root/app/
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME
COPY *.tf $APP_HOME
CMD cd ${WORKING_DIR} && terraform init -backend-config="conn_str=${DATABASE_URL}" && terraform apply -auto-approve -var="email=${EMAIL}" -var="api_key=${API_KEY}"