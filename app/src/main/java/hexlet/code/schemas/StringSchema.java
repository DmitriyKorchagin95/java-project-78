package hexlet.code.schemas;

public class StringSchema {
    private boolean required;
    private Integer minLength;
    private String contains;

    public boolean isValid(String value) {
        if (required && (value == null || value.isEmpty())) {
            return false;
        }

        if (!required && value == null) {
            return true;
        }

        if (required && minLength != null && value.length() < minLength) {
            return false;
        }

        if (required && contains != null && !value.contains(contains)) {
            return false;
        }

        return true;
    }

    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String string) {
        contains = string;
        return this;
    }
}
