package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberSchemaTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    @DisplayName("Basic: simple number is valid")
    void basicValid() {
        var schema = validator.number();
        
        assertTrue(schema.isValid(5));
    }

    @Test
    @DisplayName("Non-required: null is valid, even with positive()")
    void nonRequired() {
        var schema = validator.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));
    }

    @Test
    @DisplayName("Required: null is invalid, positive number is valid")
    void required() {
        var schema = validator.number().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    @DisplayName("positive(): negative and zero are invalid")
    void positiveRule() {
        var schema = validator.number().positive();

        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    @DisplayName("range(): lower and upper bounds inclusive")
    void range() {
        var schema = validator.number().range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));

        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
