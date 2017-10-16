package generator.backtracking;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FieldProvider {
    private Field[][] fields;

    public FieldProvider() {
        fields = new Field[9][9];
        for (int i = 0; i < fields.length; i++)
            for (int j = 0; j < fields[i].length; j++)
                fields[i][j] = new Field(i, j);

        Stream.of(fields)
                .forEach(fieldsRow -> Stream.of(fieldsRow)
                        .forEach(field -> {
                            field.initCrossFields(fields);
                            field.initBoxFields(fields);
                        }));
    }

    public void setPresets(Integer[][] presets) {
        for (int i = 0; i < presets.length; i++) {
            for (int j = 0; j < presets[i].length; j++) {
                fields[i][j].setValue(presets[i][j]);
            }
        }
    }

    public Field getField(Integer row, Integer column) {
        return fields[row][column];
    }

    public List<Field> getFieldsByBox(Integer boxRow, Integer boxColumn) {
        return Stream.of(fields)
                .flatMap(fieldsRow -> Stream.of(fieldsRow))
                .filter(field -> field.getBoxRow().equals(boxRow))
                .filter(field -> field.getBoxColumn().equals(boxColumn))
                .collect(Collectors.toList());
    }

    public List<Field> getAllFields() {
        return Stream.of(fields)
                .flatMap(fieldsRow -> Stream.of(fieldsRow))
                .collect(Collectors.toList());
    }
}
