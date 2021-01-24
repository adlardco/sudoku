package main

import (
	"fmt"
	"log"
	"net/http"
	"os"

	"pkg/config"
	"pkg/controller/grid"
	"pkg/controller/info"

	"github.com/gorilla/handlers"
	"github.com/gorilla/mux"
)

func addAPIHandlers(router *mux.Router) {
	router.HandleFunc("/api/info", info.Get).Methods(http.MethodGet)
	router.HandleFunc("/api/grid", grid.Post).Methods(http.MethodPost)
}

func addUIHandlers(router *mux.Router) {
	fs := http.FileServer(http.Dir("./ui"))
	paths := []string{"/", "/{.+}.json", "/static/js/{.+}.js"}
	for _, path := range paths {
		router.Handle(path, fs).Methods(http.MethodGet)
	}
}

func addHandlers(router *mux.Router) {
	addAPIHandlers(router)
	addUIHandlers(router)
}

func getHandler(router *mux.Router) http.Handler {
	cors := handlers.AllowedOrigins([]string{"*"})
	loggedRouter := handlers.LoggingHandler(os.Stdout, router)
	handler := handlers.CORS(cors)(loggedRouter)
	return handlers.CompressHandler(handler)
}

func main() {
	fmt.Printf("Starting server\n")
	router := mux.NewRouter()
	addHandlers(router)
	handler := getHandler(router)
	config := config.New()
	fmt.Printf("Listening on port %d\n", config.Port)
	if err := http.ListenAndServe(fmt.Sprintf(":%d", config.Port), handler); err != nil {
		log.Fatal(err)
	}
}
