package generator.backtracking;

import generator.Generator;

import java.util.*;

public class BacktrackingGenerator implements Generator {

    private FieldProvider fieldProvider;
    private BoxProvider boxProvider;
    private ValueProvider valueProvider;

    BacktrackingGenerator() {
        fieldProvider = new FieldProvider();
        boxProvider = new BoxProvider(fieldProvider);
        valueProvider = new ValueProvider();
    }

    @Override
    public Integer[][] generate() {
        DefaultGroundContainer container = new DefaultGroundContainer();
        recursiveFill(container, boxProvider.firstBox(), 1);
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

    private boolean recursiveFill(DefaultGroundContainer container, Box currentBox, Integer currentValue) {
        List<Field> actualBoxPossibleFields = currentBox.getEmptyFields();

        if (!actualBoxPossibleFields.isEmpty()) {
            Collections.shuffle(actualBoxPossibleFields);
            Iterator<Field> emptyFieldsIterator = actualBoxPossibleFields.iterator();
            while (emptyFieldsIterator.hasNext()) {
                Field nextField = emptyFieldsIterator.next();
                if (!nextField.isSatisfied(currentValue)) {
                    // This field is not satisfied (value collision in row / column / box), try another.
                    continue;
                }
                // This field is satisfied. Set value and continue with next box.
                nextField.setValue(currentValue);
                Box nextBox = boxProvider.nextBox(currentBox, currentValue);
                if (nextBox == null) {
                    // If the next box is null then whole ground is filled and it is finished - return true.
                    return true;
                }
                Integer nextValue = valueProvider.nextValue(currentBox, currentValue);
                boolean nextFill = recursiveFill(container, nextBox, nextValue);
                if (nextFill == true) {
                    // Recursive way back positive - last value was set and there is nothing to fill - return true.
                    return true;
                } else {
                    // Recursive way back negative - this way was not satisfied (there was no option to find
                    // satisfied numbers layout - empty the field and try another possible number if some exists.
                    nextField.setEmpty();
                }
            }
        }
        // If current box is last and is full then whole ground is full and filling is finished - return true.
        if (currentBox.isLast() && currentBox.isFull()) {
            return true;
        } else return false;    // Otherwise it is not filled and no possible fields left to try this branch
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
