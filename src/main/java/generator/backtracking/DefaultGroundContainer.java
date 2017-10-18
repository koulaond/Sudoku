package generator.backtracking;

import generator.GroundContainer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class DefaultGroundContainer implements GroundContainer {
    private Integer[][] numbers;

    DefaultGroundContainer() {
        numbers = new Integer[9][9];
    }

    @Override
    public Integer getNumber(Integer row, Integer column) {
        if (row > 0 && row < 9 && column > 0 && column < 9) {
            return numbers[row][column];
        } else {
            throw new IllegalArgumentException(String.format("Bad index values: %s, %s", row, column));
        }
    }

    @Override
    public Integer getNumber(Integer boxRow, Integer boxColumn, Integer inBoxRow, Integer inBoxColumn) {
        return getNumber(boxRow * 3 + inBoxRow, boxColumn * 3 + inBoxColumn);
    }

    @Override
    public List<Integer> getRowNumbers(Integer row) {
        return asList(numbers[row]);
    }

    @Override
    public List<Integer> getColumnNumbers(Integer column) {
        return Stream.of(numbers)
                .map(numbersRow -> numbersRow[column])
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getBoxNumbers(Integer boxRow, Integer boxColumn) {
        return IntStream.range(boxRow * 3, boxRow * 3 + 3)
                .mapToObj(i -> numbers[i])
                .flatMap(numbersRow -> IntStream.range(boxColumn * 3, boxColumn * 3 + 3)
                        .mapToObj(i -> numbersRow[i]))
                .collect(Collectors.toList());
    }

    void setNumber(Integer row, Integer column, Integer value) {
        if (row > 0 && row < 9 && column > 0 && column < 9) {
            numbers[row][column] = value;
        } else {
            throw new IllegalArgumentException(String.format("Bad index values: %s, %s", row, column));
        }
    }

    void setNumber(Integer boxRow, Integer boxColumn, Integer inBoxRow, Integer inBoxColumn, Integer value){
        setNumber(boxRow * 3 + inBoxRow, boxColumn * 3 + inBoxColumn, value);
    }

    void setNumber(Box box, Integer inBoxRow, Integer inBoxColumn, Integer value){
        setNumber(box.getBoxRow(), box.getBoxColumn(), inBoxRow, inBoxColumn, value);
    }
}
