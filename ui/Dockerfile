FROM alpine:3.12.1
RUN apk add --update npm
WORKDIR /src
COPY package.json tsconfig.json /src/
COPY src /src/src
COPY public /src/public
RUN npm install
RUN npm test -- --watchAll=false
RUN npm run build