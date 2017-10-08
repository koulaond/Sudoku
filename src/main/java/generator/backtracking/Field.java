package generator.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Field {
    private final Integer row;
    private final Integer column;
    private final Integer boxRow;
    private final Integer boxColumn;
    private Integer value;
    private List<Field> crossFields;
    private List<Field> boxFields;

    public Field(Integer row, Integer column) {
        this.row = row;
        this.column = column;
        this.boxRow = row / 3;
        this.boxColumn = column / 3;
    }

    public void initCrossFields(Field[][] fields) {
        crossFields = Stream.of(fields)
                .flatMap(fieldsRow -> Stream.of(fieldsRow))
                .filter(field -> (field.getRow().equals(this.getRow()) || field.getColumn().equals(this.getColumn())))
                .filter(field -> !(field.getRow().equals(this.getRow()) && field.getColumn().equals(this.getColumn())))
                .collect(toList());
    }

    public void initBoxFields(Field[][] fields) {
        boxFields = Stream.of(fields)
                .flatMap(fieldsRow -> Stream.of(fieldsRow))
                .filter(field -> field.getRow() / 3 == getRow() / 3)
                .filter(field -> field.getColumn() / 3 == getColumn() / 3)
                .filter(field -> !(field.getRow().equals(this.getRow()) && field.getColumn().equals(this.getColumn())))
                .collect(toList());
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

    public boolean isEmpty() {
        return value == null;
    }

    public void setEmpty() {
        this.value = null;
    }

    public List<Field> getCrossFields() {
        return crossFields;
    }

    public List<Field> getBoxFields() {
        return boxFields;
    }

    public Integer getBoxRow() {
        return boxRow;
    }

    public Integer getBoxColumn() {
        return boxColumn;
    }

    public List<Field> getAssociatedFields() {
        return Stream.concat(getCrossFields().stream(), getBoxFields().stream())
                .collect(toList());
    }

    public boolean isSatisfied(Integer value){
        return getAssociatedFields().stream()
                .noneMatch(field -> value.equals(field.getValue()));
    }
}

