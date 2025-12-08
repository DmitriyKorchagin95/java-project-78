package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", map -> map == null || map.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> rules) {
        Objects.requireNonNull(rules, "Shaped schema cannot be null");
        addCheck("shape", map -> map == null || isShapeValid(map, rules));
        return this;
    }

    private boolean isShapeValid(Map<?, ?> map, Map<String, BaseSchema<?>> rules) {
        return rules.entrySet().stream()
                .allMatch(entry -> {
                    String key = entry.getKey();
                    BaseSchema<?> schema = entry.getValue();
                    return schema.isValid(map.get(key));
                });
    }
}
