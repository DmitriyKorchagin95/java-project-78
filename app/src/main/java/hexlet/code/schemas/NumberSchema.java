package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(Integer from, Integer to) {
        addCheck("range", value -> value == null || (value >= from && value <= to));
        return this;
    }
}
