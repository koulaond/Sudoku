package generator.backtracking;

public class ValueProvider {

    public Integer nextValue(Box box, Integer currentValue){
        boolean isLast = box.isLast();
        if (isLast && currentValue == 9) return null;
        else return isLast ? ++currentValue : currentValue;
    }
}
