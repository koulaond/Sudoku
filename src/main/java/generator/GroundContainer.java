package generator;

import java.util.List;

public interface GroundContainer {
    Integer getNumber(Integer row, Integer column);

    Integer getNumber(Integer boxRow, Integer boxColumn, Integer inBoxRow, Integer inBoxColumn);

    List<Integer> getRowNumbers(Integer row);

    List<Integer> getColumnNumbers(Integer column);

    List<Integer> getBoxNumbers(Integer boxRow, Integer boxColumn);
}
