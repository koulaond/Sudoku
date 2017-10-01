package generator.backtracking;

import generator.Generator;

import java.util.*;

public class BacktrackingGenerator implements Generator {

    private final Point[][] ground = new Point[9][9];
    private final Box[][] boxes = new Box[3][3];

    public BacktrackingGenerator() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ground[i][j] = new Point(i, j);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boxes[i][j] = new Box(i, j, ground);
            }
        }
    }

    private boolean recursiveFill(Box box, Integer value) {
        ArrayList<Point> actualBoxPossibleFields = box.getEmptyFields();

        if (!actualBoxPossibleFields.isEmpty()) {
            Collections.shuffle(actualBoxPossibleFields);
            Iterator<Point> emptyFieldsIterator = actualBoxPossibleFields.iterator();
            while (emptyFieldsIterator.hasNext()) {
                Point nextField = emptyFieldsIterator.next();
                if (!isSatisfied(nextField, value)) {
                    continue;
                }
                nextField.setValue(value);
                Box nextBox = nextBox(box.getBoxRow(), box.getBoxColumn(), value);
                if (nextBox == null) {
                    return true;
                }
                Integer nextValue = nextValue(box.getBoxRow(), box.getBoxColumn(), value);
                boolean nextFill = recursiveFill(nextBox, nextValue);
                if (nextFill == true) {
                    return true;
                } else {
                    nextField.setValue(0);
                }
            }
        }
        if (box.isLast() && box.isFull()) {
            return true;
        } else return false;

    }

    private boolean isSatisfied(Point field, final Integer value) {
        List<Point> crossFields = getCrossFields(field);
        return !crossFields.stream()
                .anyMatch(actual -> actual.getValue() == value);
    }

    private List<Point> getCrossFields(Point field) {
        int rowCoord = field.getRow();
        int columnCoord = field.getColumn();
        List<Point> crossFields = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (i != rowCoord) crossFields.add(ground[i][columnCoord]);
            if (i != columnCoord) crossFields.add(ground[rowCoord][i]);
        }
        return crossFields;
    }

    private Box nextBox(Integer boxRow, Integer boxColumn, Integer valueFor) {
        if (boxRow == 2 && boxColumn == 2 && valueFor == 9) return null;
        else if (boxRow == 2 && boxColumn == 2) return boxes[0][0];
        else if (boxColumn == 2) return boxes[boxRow + 1][0];
        else return boxes[boxRow][boxColumn + 1];
    }

    private Integer nextValue(Integer boxRow, Integer boxColumn, Integer valueFor) {
        if (boxRow == 2 && boxColumn == 2 && valueFor == 9) return null;
        else if (boxRow == 2 && boxColumn == 2) ++valueFor;
        return valueFor;
    }

    public int[][] generate() {
        recursiveFill(boxes[0][0], 1);
        print();
        return null;
    }

    private void print() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println();
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print(" ");
                System.out.print(ground[i][j].getValue() + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        new BacktrackingGenerator().generate();
    }

}
