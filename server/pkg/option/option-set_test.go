package option

import "testing"

func TestGetSize(t *testing.T) {
	options := []int{2, 3, 5}
	set := New(options)
	if size, expectedSize := set.GetSize(), len(options); size != expectedSize {
		t.Errorf("Expected size %d but got %d", expectedSize, size)
	}
}
