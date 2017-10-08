package generator.backtracking;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Box {
    private final Integer boxRow;
    private final Integer boxColumn;
    private final Field[][] fields;

    public Box(Integer boxRow, Integer boxColumn, Field[][] fields) {
        this.boxRow = boxRow;
        this.boxColumn = boxColumn;
        this.fields = new Field[3][3];
        loadFields(fields);
    }

    private void loadFields(Field[][] fields) {
        Integer startX = boxRow * 3;
        Integer startY = boxColumn * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Field actual = fields[startX + i][startY + j];
                this.fields[i][j] = actual;
            }
        }
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
        return Stream.of(fields).flatMap(fieldsRow -> Stream.of(fieldsRow))
                .filter(Field::isEmpty)
                .collect(Collectors.toList());
    }
}
