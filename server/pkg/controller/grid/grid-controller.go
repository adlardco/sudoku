package grid

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
)

// Grid is a grid of cells
type Grid struct {
	CellValues []int `json:"cellValues"`
}

// Post posts a new grid to be solved
func Post(writer http.ResponseWriter, request *http.Request) {
	body, err := ioutil.ReadAll(request.Body)
	defer request.Body.Close()
	if err != nil {
		http.Error(writer, err.Error(), 500)
		return
	}
	var grid Grid
	if err := json.Unmarshal(body, &grid); err != nil {
		fmt.Println(err)
		return
	}
	json, err := json.Marshal(grid)
	if err != nil {
		http.Error(writer, err.Error(), 500)
		return
	}
	writer.Header().Set("Content-Type", "application/json")
	fmt.Fprintf(writer, string(json))
}
