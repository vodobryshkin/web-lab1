import com.google.gson.JsonSyntaxException;
import enteties.request.records.ValidationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import codec.implementations.json.JsonParser;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования парсера JSON.
 */
class JsonParserTest {
    private final JsonParser parser = new JsonParser();

    @Test
    @DisplayName("Успешный парсинг корректного JSON")
    void parse_validJson_returnsValidationRequest() {
        String json = """
                {"x": 1.5, "y": -2.25, "r": 3.0}
                """;

        ValidationRequest result = parser.parse(json);

        assertAll(
                () -> assertEquals(1.5, result.x(), 1e-9),
                () -> assertEquals(-2.25, result.y(), 1e-9),
                () -> assertEquals(3.0, result.r(), 1e-9)
        );
    }

    @Test
    @DisplayName("Целочисленные значения парсятся как Double")
    void parse_integerValues_parsedAsDoubles() {
        String json = """
                {"x": 1, "y": 0, "r": 5}
                """;

        ValidationRequest result = parser.parse(json);

        assertAll(
                () -> assertEquals(1.0, result.x(), 1e-9),
                () -> assertEquals(0.0, result.y(), 1e-9),
                () -> assertEquals(5.0, result.r(), 1e-9)
        );
    }

    @Test
    @DisplayName("Числа в виде строк тоже поддерживаются GSON")
    void parse_stringNumbers_parsedAsDoubles() {
        String json = """
                {"x": "2.75", "y": "-3.5", "r": "1.25"}
                """;

        ValidationRequest result = parser.parse(json);

        assertAll(
                () -> assertEquals(2.75, result.x(), 1e-9),
                () -> assertEquals(-3.5, result.y(), 1e-9),
                () -> assertEquals(1.25, result.r(), 1e-9)
        );
    }

    @Test
    @DisplayName("Отсутствующее поле приводит к NullPointerException (obj.get(..) == null)")
    void parse_missingField_throwsNullPointerException() {
        String json = """
                {"x": 1.0, "y": 2.0}
                """;
        assertThrows(NullPointerException.class, () -> parser.parse(json));
    }

    @Test
    @DisplayName("Нечисловое значение приводит к NumberFormatException")
    void parse_nonNumeric_throwsNumberFormatException() {
        String json = """
                {"x": "not-a-number", "y": 1.0, "r": 2.0}
                """;
        assertThrows(NumberFormatException.class, () -> parser.parse(json));
    }

    @Test
    @DisplayName("Некорректный JSON приводит к JsonSyntaxException")
    void parse_invalidJson_throwsJsonSyntaxException() {
        String json = "{ x: 1.0, y: 2.0, r: 3.0 ";
        assertThrows(JsonSyntaxException.class, () -> parser.parse(json));
    }

    @Test
    @DisplayName("null-строка приводит к NullPointerException (Gson вернёт null-объект)")
    void parse_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }
}
