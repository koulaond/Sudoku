package generator.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final Integer row;
    private final Integer column;
    private Integer value;
    private List<Field> crossFields;

    public Field(Integer row, Integer column) {
        this.row = row;
        this.column = column;
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

    public boolean isEmpty(){
        return value == null;
    }

    public void setEmpty(){
        this.value = null;
    }

    public void initCrossFields(Field[][] fields) {
        crossFields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (i != row) crossFields.add(fields[i][column]);
            if (i != column) crossFields.add(fields[row][i]);
        }
    }

    public List<Field> getCrossFields() {
        return crossFields;
    }
}

