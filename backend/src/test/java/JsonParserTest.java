import com.google.gson.JsonSyntaxException;
import enteties.request.records.ValidationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import codec.implementations.json.JsonParser;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования парсера JSON.
 */
class JsonParserTest {
    private final JsonParser parser = new JsonParser();

    @Test
    @DisplayName("Успешный парсинг корректного JSON")
    void parseValidJsonReturnsValidationRequest() {
        String json = """
                {"x": 1.5, "y": -2.25, "r": 3.0}
                """;

        ValidationRequest result = parser.parse(json);

        assertAll(
                () -> assertEquals(new BigDecimal("1.5"), result.x()),
                () -> assertEquals(new BigDecimal("-2.25"), result.y()),
                () -> assertEquals(new BigDecimal("3.0"), result.r())
        );
    }

    @Test
    @DisplayName("Целочисленные значения парсятся как BigDecimal")
    void parseIntegerValuesParsedAsBigDecimal() {
        String json = """
                {"x": 1, "y": 0, "r": 5}
                """;

        ValidationRequest result = parser.parse(json);

        assertAll(
                () -> assertEquals(new BigDecimal("1"), result.x()),
                () -> assertEquals(new BigDecimal("0"), result.y()),
                () -> assertEquals(new BigDecimal("5"), result.r())
        );
    }

    @Test
    @DisplayName("Числа в виде строк тоже поддерживаются GSON")
    void parseStringNumbersParsedAsBigDecimal() {
        String json = """
                {"x": "2.75", "y": "-3.5", "r": "1.25"}
                """;

        ValidationRequest result = parser.parse(json);

        assertAll(
                () -> assertEquals(new BigDecimal("2.75"), result.x()),
                () -> assertEquals(new BigDecimal("-3.5"), result.y()),
                () -> assertEquals(new BigDecimal("1.25"), result.r())
        );
    }

    @Test
    @DisplayName("Отсутствующее поле приводит к NullPointerException (obj.get(..) == null)")
    void parseMissingFieldThrowsNullPointerException() {
        String json = """
                {"x": 1.0, "y": 2.0}
                """;
        assertThrows(NullPointerException.class, () -> parser.parse(json));
    }

    @Test
    @DisplayName("Нечисловое значение приводит к NumberFormatException")
    void parseNonNumericThrowsNumberFormatException() {
        String json = """
                {"x": "not-a-number", "y": 1.0, "r": 2.0}
                """;
        assertThrows(NumberFormatException.class, () -> parser.parse(json));
    }

    @Test
    @DisplayName("Некорректный JSON приводит к JsonSyntaxException")
    void parseInvalidJsonThrowsJsonSyntaxException() {
        String json = "{ x: 1.0, y: 2.0, r: 3.0 ";
        assertThrows(JsonSyntaxException.class, () -> parser.parse(json));
    }

    @Test
    @DisplayName("null-строка приводит к NullPointerException (Gson вернёт null-объект)")
    void parseNullInputThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }
}
