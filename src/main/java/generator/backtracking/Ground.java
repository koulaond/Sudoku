package generator.backtracking;

import java.util.stream.Stream;

public class Ground {
    private final Field[][] fields;
    private final Box[][] boxes;

    public Ground() {
        fields = new Field[9][9];
        boxes = new Box[3][3];
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = new Field(i, j);
            }
        }
        Stream.of(fields)
                .forEach(fieldsRow -> Stream.of(fieldsRow)
                        .forEach(field -> field.initCrossFields(fields)));

        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[i].length; j++) {
                boxes[i][j] = new Box(i, j, fields);
            }
        }
    }

    public Box nextBox(Integer boxRow, Integer boxColumn, Integer valueFor) {
        if (boxRow == 2 && boxColumn == 2 && valueFor == 9) return null;
        else if (boxRow == 2 && boxColumn == 2) return boxes[0][0];
        else if (boxColumn == 2) return boxes[boxRow + 1][0];
        else return boxes[boxRow][boxColumn + 1];
    }

    public Box getFirstBox(){
        return boxes[0][0];
    }

    public Integer nextValue(Integer boxRow, Integer boxColumn, Integer valueFor) {
        if (boxRow == 2 && boxColumn == 2 && valueFor == 9) return null;
        else if (boxRow == 2 && boxColumn == 2) ++valueFor;
        return valueFor;
    }

    public int[][] getResult(){
        int[][] result = new int[fields.length][];
        for (int i = 0; i < fields.length; i++) {
            result[i] = new int[fields[i].length];
            for (int j = 0; j < fields[i].length; j++) {
                result[i][j] = fields[i][j].getValue();
            }
        }
        return result;
    }
}