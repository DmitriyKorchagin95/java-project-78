package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", map -> map == null || map.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> rules) {
        Objects.requireNonNull(rules, "Shaped schema cannot be null");
        addCheck("shape", map -> map == null || isShapeValid(map, rules));
        return this;
    }

    private boolean isShapeValid(Map<?, ?> map, Map<String, ? extends BaseSchema<?>> rules) {
        return rules.entrySet().stream()
                .allMatch(entry -> {
                    var key = entry.getKey();
                    var schema = entry.getValue();
                    return schema.isValid(map.get(key));
                });
    }
}
