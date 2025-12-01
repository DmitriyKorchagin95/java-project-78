package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MapSchemaTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    @DisplayName("Non-required: null should be valid")
    void nonRequired() {
        var schema = validator.map();

        assertTrue(schema.isValid(null));
    }

    @Test
    @DisplayName("Required: null should be invalid, empty map valid")
    void required() {
        var schema = validator.map().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));
    }

    @Test
    @DisplayName("sizeof(): validates map size according to example")
    void sizeofRule() {
        var schema = validator.map().sizeof(2);

        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");

        assertTrue(schema.isValid(data));
    }
}

