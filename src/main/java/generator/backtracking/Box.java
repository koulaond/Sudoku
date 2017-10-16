package generator.backtracking;

import java.util.List;
import java.util.stream.Collectors;

public class Box {
    private final Integer boxRow;
    private final Integer boxColumn;
    private final List<Field> boxFields;

    public Box(Integer boxRow, Integer boxColumn, List<Field> boxFields) {
        this.boxRow = boxRow;
        this.boxColumn = boxColumn;
        this.boxFields = boxFields;
    }

    public Integer getBoxRow() {
        return boxRow;
    }

    public Integer getBoxColumn() {
        return boxColumn;
    }

    public boolean isFull() {
        return getEmptyFields().isEmpty();
    }

    public boolean isLast() {
        return boxRow == 2 && boxColumn == 2;
    }

    public List<Field> getEmptyFields() {
        return boxFields.stream()
                .filter(Field::isEmpty)
                .collect(Collectors.toList());
    }
}
