package option

// Options Records possible values of a cell
type Options struct {
	Values []int
}

// New Creates and returns a set of option values
func New(values []int) Options {
	return Options{make([]int, len(values))}
}

// GetSize return the number of options
func (options Options) GetSize() int {
	return len(options.Values)
}
