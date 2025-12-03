package hexlet.code.schemas;

public class StringSchema {
    private boolean requiredEnabled;
    private Integer minLength;
    private String contains;

    public boolean isValid(String value) {
        if (requiredEnabled && (value == null || value.isEmpty())) {
            return false;
        }

        if (!requiredEnabled && value == null) {
            return true;
        }

        if (requiredEnabled && minLength != null && value.length() < minLength) {
            return false;
        }

        if (requiredEnabled && contains != null && !value.contains(contains)) {
            return false;
        }

        return true;
    }

    public StringSchema required() {
        this.requiredEnabled = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String string) {
        this.contains = string;
        return this;
    }
}
