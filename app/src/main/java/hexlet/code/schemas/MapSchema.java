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

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        Objects.requireNonNull(schemas, "Shaped schema cannot be null");
        addCheck("shape", map -> map == null || isShapeValid(map, schemas));
        return this;
    }

    private <T> boolean isShapeValid(Map<?, ?> map, Map<String, BaseSchema<T>> schemas) {
        return schemas.entrySet().stream()
                .allMatch(entry -> {
                    var key = entry.getKey();
                    var schema = entry.getValue();

                    @SuppressWarnings("unchecked")
                    T value = (T) map.get(key);

                    return schema.isValid(value);
                });
    }
}
