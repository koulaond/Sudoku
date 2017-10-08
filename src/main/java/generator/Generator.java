package generator;

public interface Generator {

    /**
     * Generates sudoku ground with all filled fields.
     * @return Two-dimensional array with numbers
     */
    Integer[][] generate();

    /**
     * Generates sudoku ground with all filled fields using a preset values. Integer value must be in range 0-9
     * or null for non-filled field.
     * @param preset preset values
     * @throws IllegalStateException if there there are some inconsistent values in preset fields
     * @return Two-dimensional array with numbers
     */
    Integer[][] generate(Integer[][] preset) throws IllegalStateException;
}
