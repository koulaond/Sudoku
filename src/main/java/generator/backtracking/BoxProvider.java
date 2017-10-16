package generator.backtracking;

public class BoxProvider {
    private Box[][] boxes;

    BoxProvider(FieldProvider fieldProvider) {
        boxes = new Box[3][3];
        for (int i = 0; i < boxes.length; i++)
            for (int j = 0; j < boxes[i].length; j++)
                boxes[i][j] = new Box(i,j, fieldProvider.getFieldsByBox(i,j));

    }

    public Box nextBox(Integer boxRow, Integer boxColumn, Integer valueFor) {
        if (boxRow == 2 && boxColumn == 2 && valueFor == 9) return null;
        else if (boxRow == 2 && boxColumn == 2) return firstBox();
        else if (boxColumn == 2) return boxes[boxRow + 1][0];
        else return boxes[boxRow][boxColumn + 1];
    }

    public Integer nextValue(Integer boxRow, Integer boxColumn, Integer valueFor) {
        if (boxRow == 2 && boxColumn == 2 && valueFor == 9) return null;
        else if (boxRow == 2 && boxColumn == 2) return ++valueFor;
        return valueFor;
    }

    public Box firstBox() {
        return boxes[0][0];
    }
}
