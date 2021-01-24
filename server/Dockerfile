FROM golang:alpine AS build
RUN apk update && apk add --no-cache git
COPY *.go /go/src/
COPY pkg /go/src/pkg/
WORKDIR /go/src
RUN go get -d -v ./...
RUN GOOS=linux GOARCH=386 go test -v -cover ./pkg/...
RUN GOOS=linux GOARCH=386 go build -v -o /app -ldflags="-s -w"

FROM busybox:1.33.0
COPY --from=sudoku-ui:latest /src/build /ui
COPY --from=build /app /
ENTRYPOINT ["/app"]