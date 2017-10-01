package generator.backtracking;

import generator.Generator;

public class BacktrackingGenerator implements Generator {

    private final int[][] ground = new int[9][9];
    private final Box[][] boxes = new Box[3][3];

    public BacktrackingGenerator() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boxes[i][j] = new Box(i, j, ground);
            }
        }
    }

    private boolean recursiveFill(int boxRow, int boxColumn, int value) {
        Box actualBox = boxes[boxRow][boxColumn];
        int[][] actualBoxEmptyFields = actualBox.getEmptyFields();

        boolean[][] triedFields = new boolean[3][3];

        if (actualBox.isLast() && actualBox.isFull()) return true;
        else return false;
    }

    public int[][] generate() {
        recursiveFill(0, 0, 1);
        return ground;
    }

}
