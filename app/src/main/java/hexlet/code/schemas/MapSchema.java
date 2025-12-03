package hexlet.code.schemas;

import java.util.Map;

public class MapSchema {
    private boolean requiredEnabled = false;
    private boolean sizeofEnabled = false;
    private int expectedSize = 0;
    private boolean shapeEnabled = false;
    private Map<String, StringSchema> shapeRules;

    public MapSchema required() {
        this.requiredEnabled = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        this.sizeofEnabled = true;
        this.expectedSize = size;
        return this;
    }

    public void shape(Map<String, StringSchema> rules) {
        this.shapeEnabled = true;
        this.shapeRules = rules;
    }

    public boolean isValid(Map<String, String> value) {

        if (value == null) {
            return !requiredEnabled;
        }

        if (sizeofEnabled) {
            if (value.size() != expectedSize) {
                return false;
            }
        }

        if (shapeEnabled) {
            for (var entry : shapeRules.entrySet()) {

                String key = entry.getKey();
                var schema = entry.getValue();

                if (!schema.isValid(value.get(key))) {
                    return false;
                }
            }
        }

        return true;
    }
}
