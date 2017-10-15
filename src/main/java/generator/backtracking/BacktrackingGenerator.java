package generator.backtracking;

import generator.Generator;

import java.util.*;

public class BacktrackingGenerator implements Generator {

    private FieldProvider fieldProvider;
    private Box[][] boxes;

    @Override
    public Integer[][] generate() {
        fieldProvider = new FieldProvider();
    }

    @Override
    public Integer[][] generate(Integer[][] presets) throws IllegalStateException {
        fieldProvider = new FieldProvider(presets);
        boxes = new Box[3][3];
        createBoxes();
        recursiveFill(getFirstBox(), 1);
        return fieldProvider.convertToInts();
    }

    private void validatePreset(Integer[][] preset) {
        for (int i = 0; i < preset.length; i++) {
            for (int j = 0; j < preset[i].length; j++) {
                // TODO
            }
        }
    }
// TODO separate this block into a class
    private Box nextBox(Integer boxRow, Integer boxColumn, Integer valueFor) {
        if (boxRow == 2 && boxColumn == 2 && valueFor == 9) return null;
        else if (boxRow == 2 && boxColumn == 2) return getFirstBox();
        else if (boxColumn == 2) return boxes[boxRow + 1][0];
        else return boxes[boxRow][boxColumn + 1];
    }

    private Box getFirstBox() {
        return boxes[0][0];
    }
// TODO separate this block into a class
    private Integer nextValue(Integer boxRow, Integer boxColumn, Integer valueFor) {
        if (boxRow == 2 && boxColumn == 2 && valueFor == 9) return null;
        else if (boxRow == 2 && boxColumn == 2) return ++valueFor;
        return valueFor;
    }

    private boolean recursiveFill(Box box, Integer value) {
        List<Field> actualBoxPossibleFields = box.getEmptyFields();

        if (!actualBoxPossibleFields.isEmpty()) {
            Collections.shuffle(actualBoxPossibleFields);
            Iterator<Field> emptyFieldsIterator = actualBoxPossibleFields.iterator();
            while (emptyFieldsIterator.hasNext()) {
                Field nextField = emptyFieldsIterator.next();
                if (!nextField.isSatisfied(value)) {
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
                    nextField.setEmpty();
                }
            }
        }
        if (box.isLast() && box.isFull()) {
            return true;
        } else return false;
    }

    private void createBoxes() {

    }

    public static void main(String[] args) {
        Integer[][] ground = new BacktrackingGenerator().generate();
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
