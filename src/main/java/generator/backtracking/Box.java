package generator.backtracking;

import java.util.ArrayList;

public class Box {
    private final Integer boxRow;
    private final Integer boxColumn;
    private final Field[][] fields;

    public Box(Integer boxRow, Integer boxColumn, Field[][] fields) {
        this.boxRow = boxRow;
        this.boxColumn = boxColumn;
        this.fields = fields;
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

    public ArrayList<Field> getEmptyFields() {
        ArrayList<Field> emptyFields = new ArrayList();
        Integer startX = boxRow * 3;
        Integer startY = boxColumn * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Field actual = fields[startX + i][startY + j];
                if (actual.isEmpty()) {
                    emptyFields.add(actual);
                }
            }
        }
        return emptyFields;
    }
}
