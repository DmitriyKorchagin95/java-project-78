package hexlet.code.schemas;

public class NumberSchema {
    private boolean required;
    private boolean positive;
    private boolean range;
    private Integer from;
    private Integer to;

    public boolean isValid(Integer value) {

        if (value == null) {
            return !required;
        }

        if (positive && value <= 0) {
            return false;
        }

        if (range && (value < from || value > to)) {
            return false;
        }

        return true;
    }



    public NumberSchema required() {
        required = true;
        return this;
    }

    public NumberSchema positive() {
        positive = true;
        return this;
    }

    public NumberSchema range(Integer from, Integer to) {
        this.range = true;
        this.from = from;
        this.to = to;
        return this;
    }
}
