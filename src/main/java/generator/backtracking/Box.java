package generator.backtracking;

import java.util.ArrayList;
import java.util.Arrays;

public class Box {
    private final Integer boxRow;
    private final Integer boxColumn;
    private final Point[][] ground;

    public Box(Integer boxRow, Integer boxColumn, Point[][] ground) {
        this.boxRow = boxRow;
        this.boxColumn = boxColumn;
        this.ground = ground;
    }

    public Integer getBoxRow() {
        return boxRow;
    }

    public Integer getBoxColumn() {
        return boxColumn;
    }

    public Point[][] getGround() {
        return this.ground;
    }

    public void setNumber(Integer row, Integer col, Integer value) {
        this.ground[this.boxRow * 3 + row][this.boxColumn * 3 + col].setValue(value);
    }

    public boolean isFull() {
        return getEmptyFields().isEmpty();
    }

    public boolean isLast() {
        return boxRow == 2 && boxColumn == 2;
    }

    public ArrayList<Point> getEmptyFields() {
        ArrayList<Point> emptyFields = new ArrayList();
        Integer startX = boxRow * 3;
        Integer startY = boxColumn * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Point actual = ground[startX + i][startY + j];
                if (actual.getValue() == 0) {
                    emptyFields.add(actual);
                }
            }
        }
        return emptyFields;
    }
}
