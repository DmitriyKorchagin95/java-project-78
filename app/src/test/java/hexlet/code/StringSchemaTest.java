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
    @DisplayName("Non-required schema accepts null, empty and normal string")
    void nonRequiredValid() {
        var schema = validator.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("Valid String"));
    }

    @Test
    @DisplayName("Non-required schema with minLength allows null/empty")
    void nonRequiredMinLength() {
        var schema = validator.string().minLength(5);

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
    }

    @Test
    @DisplayName("Non-required schema with contains allows null/empty")
    void nonRequiredContains() {
        var schema = validator.string().contains("abc");

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
    }

    @Test
    @DisplayName("Required schema accepts non-empty string")
    void requiredValid() {
        var schema = validator.string().required();

        assertTrue(schema.isValid("Valid String"));
    }

    @Test
    @DisplayName("Required schema rejects null and empty string")
    void requiredInvalid() {
        var schema = validator.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    @DisplayName("Contains returns true if substring is present")
    void containsValid() {
        var schema = validator.string().required().contains("Valid");

        assertTrue(schema.isValid("Valid String"));
    }

    @Test
    @DisplayName("Contains returns false if substring is missing")
    void containsInvalid() {
        var schema = validator.string().required().contains("word");

        assertFalse(schema.isValid("Valid String"));
    }

    @Test
    @DisplayName("MinLength returns true if string is long enough")
    void minLengthValid() {
        var schema = validator.string().required().minLength(5);

        assertTrue(schema.isValid("Valid String"));
    }

    @Test
    @DisplayName("MinLength returns false if string is too short")
    void minLengthInvalid() {
        var schema = validator.string().required().minLength(55);

        assertFalse(schema.isValid("Short text"));
    }

    @Test
    @DisplayName("Combined rules accept valid string")
    void combinedValid() {
        var schema = validator.string()
                .required()
                .minLength(5)
                .contains("test");

        assertTrue(schema.isValid("test string"));
    }

    @Test
    @DisplayName("Combined rules reject invalid string")
    void combinedInvalid() {
        var schema = validator.string()
                .required()
                .minLength(5)
                .contains("test");

        assertFalse(schema.isValid("string"));
    }
}
