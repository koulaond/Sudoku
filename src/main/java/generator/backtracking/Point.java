package generator.backtracking;

/**
 * Created by Koula on 1.10.2017.
 */
public class Point {
    private final Integer row;
    private final Integer column;
    private Integer value;

    public Point(Integer row, Integer column) {
        this.row = row;
        this.column = column;
        this.value = 0;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getValue() {
        return value;
    }
}

