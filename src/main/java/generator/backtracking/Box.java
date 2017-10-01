package generator.backtracking;

import java.util.ArrayList;
import java.util.Arrays;

public class Box {
    private final int boxRow;
    private final int boxColumn;
    private final int[][] ground;

    public Box(int boxRow, int boxColumn, int[][] ground) {
        this.boxRow = boxRow;
        this.boxColumn = boxColumn;
        this.ground = ground;
    }

    public int getBoxRow() {
        return boxRow;
    }

    public int getBoxColumn() {
        return boxColumn;
    }

    public int[][] getGround() {
        return this.ground;
    }

    public void setNumber(int row, int col, int value) {
        this.ground[this.boxRow * 3 + row][this.boxColumn * 3 + col] = value;
    }

    public boolean isFull() {
        return getEmptyFields().length == 0;
    }

    public boolean isLast() {
        return boxRow == 2 && boxColumn == 2;
    }

    public int[][] getEmptyFields() {
        ArrayList<Integer[]> emptyFields = new ArrayList<Integer[]>();
        int startX = boxRow * 3;
        int startY = boxColumn * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ground[startX + i][startY + j] == 0) {
                    int xCoord = startX + i;
                    int yCoord = startY + j;
                    emptyFields.add(new Integer[]{xCoord, yCoord});
                }
            }
        }
        return emptyFields.toArray(new int[emptyFields.size()][]);
    }
}
