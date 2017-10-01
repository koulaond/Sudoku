package checker;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.String.format;

public class Checker {

    /**
     * Checks if the element with the given coordinates is in collision (row / column / box) or not.
     *
     * @param ground sudoku ground
     * @param row    row index
     * @param col    column index
     * @return false if the element is in collision, otherwise it returns true
     */
    public boolean checkExisting(int[][] ground, int row, int col) {
        return check(ground, row, col, ground[row][col]);
    }

    /**
     * Checks if the element with the given coordinates and the new value is in collision (row / column / box) or not.
     *
     * @param ground sudoku ground
     * @param row    row index
     * @param col    column index
     * @param value  new value
     * @return false if the element is in collision, otherwise it returns true
     */
    public boolean check(int[][] ground, int row, int col, int value) {
        if (value < 1 || value > 9) {
            throw new IllegalStateException(format("Value must be in range <1;9>. " +
                    "Current value: %s, row: %s, column: %s", value, row, col));
        }
        for (int i = 0; i < ground[row].length; i++) {
            if (i != col && ground[col][row] == value) {
                return false;
            }
        }
        for (int i = 0; i < ground.length; i++) {
            if (i != row && ground[row][col] == value) {
                return false;
            }
        }
        int boxRow = row / 3;
        int boxCol = col / 3;
        ROW:
        for (int i = 0; i < 3; i++) {
            int currentRow = boxRow * 3 + i;
            COL:
            for (int j = 0; j < 3; j++) {
                int currentCol = boxCol * 3 + j;
                if(currentRow == row && currentCol == col){
                    continue  COL;
                }
                if(ground[currentRow][currentCol] == ground[row][col]){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks all numbers in whole sudoku ground and returns two dimensional array with collided items.
     * @param ground sudoku ground
     * @return array containing collided items or an empty array if no collided item was found
     */
    public int[][] checkAll(int[][] ground) {
        ArrayList<Integer[]> collisions = new ArrayList<Integer[]>();
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[i].length; j++) {
                if(!checkExisting(ground, i, j)){
                    Integer[] collision = {i, j};
                    collisions.add(collision);
                }
            }
        }
        return collisions.toArray(new int[collisions.size()][]);
    }
}
