package com.adlardco.sudoku.service.cell;

import com.adlardco.sudoku.service.option.OptionSetFactory;
import lombok.NonNull;

public class CellConverter {

    private final int numOptions;
    private final OptionSetFactory optionSetFactory;

    public CellConverter(int numOptions) {
        this.numOptions = numOptions;
        this.optionSetFactory = new OptionSetFactory(numOptions);
    }

    public Cell fromValue(Integer value) {
       var cell = Cell.of(value == null ? 0 : value);
       setCellOptions(cell);
       return cell;
    }

    public int toValue(@NonNull Cell cell) {
        return optionIsValid(cell) ? getOption(cell) : 0;
    }

    private void setCellOptions(Cell cell) {
        if(optionIsValid(cell)) {
            return;
        }
        var options = optionSetFactory.get();
        cell.getOptionSet().set(options);
    }

    private boolean optionIsValid(Cell cell) {
        var option = getOption(cell);
        return option > 0 && option <= numOptions;
    }

    private int getOption(Cell cell) {
        return cell.getOptionSet().toInt();
    }
}
