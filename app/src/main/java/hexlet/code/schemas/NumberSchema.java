package hexlet.code.schemas;

public class NumberSchema {
    private boolean requiredEnabled;
    private boolean positiveEnabled;
    private boolean rangeEnabled;
    private Integer from;
    private Integer to;

    public boolean isValid(Integer value) {

        if (value == null) {
            return !requiredEnabled;
        }

        if (positiveEnabled && value <= 0) {
            return false;
        }

        if (rangeEnabled && (value < from || value > to)) {
            return false;
        }

        return true;
    }



    public NumberSchema required() {
        requiredEnabled = true;
        return this;
    }

    public NumberSchema positive() {
        positiveEnabled = true;
        return this;
    }

    public NumberSchema range(Integer from, Integer to) {
        this.rangeEnabled = true;
        this.from = from;
        this.to = to;
        return this;
    }
}
