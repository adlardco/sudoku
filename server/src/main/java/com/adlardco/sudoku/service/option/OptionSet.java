package com.adlardco.sudoku.service.option;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@AllArgsConstructor
@EqualsAndHashCode
public class OptionSet {
    @NonNull private final Set<Integer> options;

    public OptionSet(@NonNull OptionSet optionSet) {
        this.options = new HashSet<>(optionSet.options);
    }

    public static OptionSet of(int... options) {
        var theOptions = new HashSet<Integer>();
        for(var option : options) {
            theOptions.add(option);
        }
        return new OptionSet(theOptions);
    }

    public static OptionSet of(@NonNull List<OptionSet> optionSets) {
        var options = optionSets.stream().flatMap(optionSet -> optionSet.options.stream()).collect(Collectors.toSet());
        return new OptionSet(options);
    }

    public int getSize() {
        return options.size();
    }

    public boolean isEmpty() {
        return getSize() < 1;
    }

    public void retain(@NonNull OptionSet optionSet) {
        this.options.retainAll(optionSet.options);
    }

    public void set(@NonNull OptionSet optionSet) {
        clear();
        options.addAll(optionSet.options);
    }

    public void set(int option) {
        checkArgument(option >= 0);

        retain(OptionSet.of(option));
    }

    public int toInt() {
        return options.size() == 1 ? options.iterator().next() : 0;
    }

    public void remove(int option) {
        checkArgument(option >= 0);

        options.remove(option);
    }

    public void remove(@NonNull OptionSet optionSet) {
        this.options.removeAll(optionSet.options);
    }

    public Optional<Integer> getNextHighest(int option) {
        checkArgument(option >= 0);

        return options.stream()
                .sorted()
                .filter(cellOption -> cellOption > option)
                .findFirst();
    }

    public void clear() {
        options.clear();
    }

    @Override
    public String toString() {
        return options.toString();
    }
}
