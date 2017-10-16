package generator.backtracking;

import generator.Generator;

import java.util.*;

public class BacktrackingGenerator implements Generator {

    private FieldProvider fieldProvider;
    private BoxProvider boxProvider;

    BacktrackingGenerator(){
        fieldProvider = new FieldProvider();
        boxProvider = new BoxProvider(fieldProvider);
    }

    @Override
    public Integer[][] generate() {
        recursiveFill(boxProvider.firstBox(), 1);
        return new FieldsConverter(fieldProvider).convertToInts();
    }

    @Override
    public Integer[][] generate(Integer[][] presets) throws IllegalStateException {
        fieldProvider.setPresets(presets);
        return generate();
    }

    private void validatePreset(Integer[][] preset) {
        for (int i = 0; i < preset.length; i++) {
            for (int j = 0; j < preset[i].length; j++) {
                // TODO
            }
        }
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
                Box nextBox = boxProvider.nextBox(box.getBoxRow(), box.getBoxColumn(), value);
                if (nextBox == null) {
                    return true;
                }
                Integer nextValue = boxProvider.nextValue(box.getBoxRow(), box.getBoxColumn(), value);
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
