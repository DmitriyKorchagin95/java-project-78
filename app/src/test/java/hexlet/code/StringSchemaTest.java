package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringSchemaTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    @DisplayName("Non-required: null and empty should be valid")
    void testNonRequired() {
        var schema = validator.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    @DisplayName("Required: null and empty should be invalid, non-empty valid")
    void testRquired() {
        var schema = validator.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    @DisplayName("contains(): substring checks according to examples")
    void testContains() {
        var schema = validator.string().required();

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
    }

    @Test
    @DisplayName("minLength(): last call overwrites previous one")
    void tesMminLengthOverwrite() {
        var schema = validator.string();

        schema.minLength(10);
        schema.minLength(4);

        assertTrue(schema.isValid("Hexlet"));
    }
}
