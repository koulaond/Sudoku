package generator.backtracking;

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

    public FieldProvider(Integer[][] presets){
        this();
        for (int i = 0; i < presets.length; i++) {
            for (int j = 0; j < presets[i].length; j++) {
                fields[i][j].setValue(presets[i][j]);
            }
        }
    }

    Integer[][] convertToInts() {
        return Stream.of(fields)
                .map(fieldsRow -> Stream.of(fieldsRow)
                        .map(field -> field.getValue())
                        .collect(Collectors.toList()).toArray(new Integer[9]))
                .collect(Collectors.toList())
                .toArray(new Integer[9][]);
    }

    public Field getField(Integer row, Integer column){
        return fields[row][column];
    }
}
