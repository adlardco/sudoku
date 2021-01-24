package config

import (
	"fmt"
	"os"
	"strconv"
)

// Config Contains configuration for the system
type Config struct {
	Port int
}

// New Get the configuration for the system
func New() Config {
	return Config{getConfigInt("PORT", 8080)}
}

func getConfigInt(key string, defaultValue int) int {
	portText := os.Getenv(key)
	if len(portText) < 1 {
		return defaultValue
	}
	port, err := strconv.Atoi(portText)
	if err != nil {
		fmt.Println(err)
		return defaultValue
	}
	return port
}
