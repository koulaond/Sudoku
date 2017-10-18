package generator.backtracking;

public class BoxProvider {
    private Box[][] boxes;

    BoxProvider(FieldProvider fieldProvider) {
        boxes = new Box[3][3];
        for (int i = 0; i < boxes.length; i++)
            for (int j = 0; j < boxes[i].length; j++)
                boxes[i][j] = new Box(i,j, fieldProvider.getFieldsByBox(i,j));

    }

    public Box nextBox(Box currentBox, Integer valueFor) {
        if (currentBox.isLast() && valueFor == 9) return null;
        else if (currentBox.isLast()) return firstBox();
        else if (currentBox.getBoxColumn() == 2) return boxes[currentBox.getBoxRow() + 1][0];
        else return boxes[currentBox.getBoxRow()][currentBox.getBoxColumn() + 1];
    }

    public Box firstBox() {
        return boxes[0][0];
    }
}
