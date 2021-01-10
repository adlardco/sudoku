FROM alpine:3.12
ENV TERRAFORM_VERSION=0.14.3
RUN apk update && apk add wget
RUN wget https://releases.hashicorp.com/terraform/${TERRAFORM_VERSION}/terraform_${TERRAFORM_VERSION}_linux_amd64.zip && \
    unzip terraform_${TERRAFORM_VERSION}_linux_amd64.zip -d /usr/bin
ENV APP_HOME=/root/app/
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME
COPY *.tf *.sh $APP_HOME
COPY entry.sh $APP_HOME
RUN chmod 777 $APP_HOME/entry.sh
ENTRYPOINT /root/app/entry.sh "${DATABASE_URL}" "${EMAIL}" "${API_KEY}"