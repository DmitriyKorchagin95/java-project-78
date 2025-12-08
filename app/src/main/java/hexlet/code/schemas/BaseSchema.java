package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> checks = new HashMap<>();

    protected final void addCheck(String name, Predicate<T> check) {
        checks.put(name, check);
    }

    @SuppressWarnings("unchecked")
    public boolean isValid(Object value) {
        T castedValue;

        try {
            castedValue = (T) value;
        } catch (ClassCastException e) {
            return false;
        }

        for (Predicate<T> check : checks.values()) {
            if (!check.test(castedValue)) {
                return false;
            }
        }
        return true;
    }
}
