package generator.backtracking;

import generator.Generator;

import java.util.*;

public class BacktrackingGenerator implements Generator {

    private final Ground ground = new Ground();

    public BacktrackingGenerator() {

    }

    private boolean recursiveFill(Box box, Integer value) {
        ArrayList<Field> actualBoxPossibleFields = box.getEmptyFields();

        if (!actualBoxPossibleFields.isEmpty()) {
            Collections.shuffle(actualBoxPossibleFields);
            Iterator<Field> emptyFieldsIterator = actualBoxPossibleFields.iterator();
            while (emptyFieldsIterator.hasNext()) {
                Field nextField = emptyFieldsIterator.next();
                if (!isSatisfied(nextField, value)) {
                    continue;
                }
                nextField.setValue(value);
                Box nextBox = ground.nextBox(box.getBoxRow(), box.getBoxColumn(), value);
                if (nextBox == null) {
                    return true;
                }
                Integer nextValue = ground.nextValue(box.getBoxRow(), box.getBoxColumn(), value);
                boolean nextFill = recursiveFill(nextBox, nextValue);
                if (nextFill == true) {
                    return true;
                } else {
                    nextField.setEmpty();
                }
            }
        }
        if (box.isLast() && box.isFull()) {
            return true;
        } else return false;

    }

    private boolean isSatisfied(Field field, final Integer value) {
        List<Field> crossFields = field.getCrossFields();
        return !crossFields.stream()
                .anyMatch(actual -> actual.getValue() == value);
    }

    public int[][] generate() {
        recursiveFill(ground.getFirstBox(), 1);
        return ground.getResult();
    }

    public static void main(String[] args) {
        int[][] ground = new BacktrackingGenerator().generate();
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println();
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print(" ");
                System.out.print(ground[i][j] + " ");
            }

            System.out.println();
        }
    }

}
