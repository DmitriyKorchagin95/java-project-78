package hexlet.code.schemas;

import java.util.Map;

public class MapSchema {
    private boolean required;
    private boolean sized;
    private Integer size;

    public boolean isValid(Map<?, ?> value) {

        if (value == null) {
            return !required;
        }

        if (sized && value.size() != size) {
            return false;
        }

        return true;
    }



    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(Integer size) {
        sized = true;
        return this;
    }
}
