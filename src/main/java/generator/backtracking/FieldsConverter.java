package generator.backtracking;

public class FieldsConverter {

    FieldProvider fieldProvider;

    public FieldsConverter(FieldProvider fieldProvider) {
        this.fieldProvider = fieldProvider;
    }

    public Integer[][] convertToInts(){
        Integer[][] result = new Integer[9][9];
        fieldProvider.getAllFields()
                .stream()
                .forEach(field -> result[field.getRow()][field.getColumn()] = field.getValue());
        return result;
    }
}
