package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Base schema class that provides generic validation mechanism.
 * Subclasses should add validation rules using {@link #addCheck(String, Predicate)}.
 * The {@link #isValid(Object)} method is final to ensure consistent validation flow.
 *
 * @param <T> type of value to validate
 */
public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> checks = new HashMap<>();

    /**
     * Registers a validation rule.
     * Subclasses use this method to add constraints.
     *
     * @param name  rule name
     * @param check validation predicate
     */
    protected final void addCheck(String name, Predicate<T> check) {
        checks.put(name, check);
    }

    /**
     * Validates value using registered rules.
     * This method is final to prevent subclasses from breaking validation pipeline.
     *
     * @param value value to validate
     * @return true if all rules pass; false otherwise
     */
    @SuppressWarnings("unchecked")
    public final boolean isValid(Object value) {
        T casted;

        try {
            casted = (T) value;
        } catch (ClassCastException e) {
            return false;
        }

        for (Predicate<T> check : checks.values()) {
            if (!check.test(casted)) {
                return false;
            }
        }
        return true;
    }
}
