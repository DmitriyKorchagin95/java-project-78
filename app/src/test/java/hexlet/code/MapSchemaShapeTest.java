package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MapSchemaShapeTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    @DisplayName("Validates nested schemas for each map key")
    void testShapeValidation() {
        var schema = validator.map();

        Map<String, BaseSchema<?>> rules = new HashMap<>();
        rules.put("firstName", validator.string().required());
        rules.put("lastName", validator.string().required().minLength(2));
        rules.put("age", validator.number().required().positive().range(18, 100));

        schema.shape(rules);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        human1.put("age", 21);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        human2.put("age", 0);
        assertFalse(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));
    }
}
