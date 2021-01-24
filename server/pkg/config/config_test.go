package config

import (
	"os"
	"strconv"
	"testing"
)

const defaultPort = 8080
const overridePort = 8081

func TestDefaults(t *testing.T) {
	config := New()
	if port, expectedPort := config.Port, defaultPort; port != expectedPort {
		t.Errorf("Expected port %d but got %d", expectedPort, port)
	}
}

func TestPort(t *testing.T) {
	defer os.Setenv("PORT", "")
	const expectedPort = overridePort
	os.Setenv("PORT", strconv.Itoa(expectedPort))
	config := New()
	if port := config.Port; port != expectedPort {
		t.Errorf("Expected port %d but got %d", expectedPort, port)
	}
}

func TestPortWithInvalidPort(t *testing.T) {
	defer os.Setenv("PORT", "")
	os.Setenv("PORT", strconv.Itoa(overridePort)+"z")
	config := New()
	if port := config.Port; port != defaultPort {
		t.Errorf("Expected port %d but got %d", defaultPort, port)
	}
}
