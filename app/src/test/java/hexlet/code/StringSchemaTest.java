package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    @DisplayName("Null and empty should be valid")
    void testNonRequired() {
        var schema = validator.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    @DisplayName("Null and empty should be invalid, non-empty valid")
    void testRequired() {
        var schema = validator.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    @DisplayName("Substring checks according to examples")
    void testContains() {
        var schema = validator.string().required();

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
    }

    @Test
    @DisplayName("Validates min string length, last call overwrites previous one")
    void testMinLength() {
        var schema = validator.string();

        schema.minLength(10);
        schema.minLength(4);

        assertTrue(schema.isValid("Hexlet"));
    }
}
