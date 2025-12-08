package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    @DisplayName("Simple number is valid")
    void testNumberValidation() {
        var schema = validator.number();

        assertTrue(schema.isValid(5));
    }

    @Test
    @DisplayName("Null is valid, even with positive()")
    void testNonRequired() {
        var schema = validator.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));
    }

    @Test
    @DisplayName("Null is invalid, positive number is valid")
    void testRequired() {
        var schema = validator.number().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    @DisplayName("Negative and zero are invalid")
    void testPositive() {
        var schema = validator.number().positive();

        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    @DisplayName("Validates lower and upper bounds(inclusive)")
    void testRange() {
        var schema = validator.number().range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));

        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
