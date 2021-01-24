package info

import (
	"encoding/json"
	"fmt"
	"net/http"
)

// Info stores the current info about the running instance
type Info struct {
	Status string
}

// Get returns info about the running instance
func Get(writer http.ResponseWriter, request *http.Request) {
	info := &Info{Status: "OK"}
	json, err := json.Marshal(info)
	if err != nil {
		http.Error(writer, err.Error(), 500)
		return
	}
	writer.Header().Set("Content-Type", "application/json")
	fmt.Fprintf(writer, string(json))
}
