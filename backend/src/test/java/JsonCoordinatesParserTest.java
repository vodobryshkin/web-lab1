import com.google.gson.JsonSyntaxException;
import entities.request.implementations.messages.ValidationRequest;
import entities.request.implementations.network.ParseRequestBodyRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import codec.implementations.json.coordinates.JsonCoordinatesParser;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования парсера JSON.
 */
class JsonCoordinatesParserTest {
    private final JsonCoordinatesParser parser = new JsonCoordinatesParser();

    @Test
    @DisplayName("Успешный парсинг корректного JSON")
    void parseValidJsonReturnsValidationRequest() {
        String data = """
                {"x": 1.5, "y": -2.25, "r": 3.0}
                """;

        ParseRequestBodyRequest json = new ParseRequestBodyRequest(data);

        ValidationRequest result = parser.parse(json);

        assertAll(
                () -> assertEquals("1.5", result.x()),
                () -> assertEquals("-2.25", result.y()),
                () -> assertEquals("3.0", result.r())
        );
    }

    @Test
    @DisplayName("Целочисленные значения парсятся как String")
    void parseIntegerValuesParsedAsString() {
        String data = """
                {"x": 1, "y": 0, "r": 5}
                """;

        ParseRequestBodyRequest json = new ParseRequestBodyRequest(data);

        ValidationRequest result = parser.parse(json);

        assertAll(
                () -> assertEquals("1", result.x()),
                () -> assertEquals("0", result.y()),
                () -> assertEquals("5", result.r())
        );
    }

    @Test
    @DisplayName("Числа в виде строк тоже поддерживаются GSON")
    void parseStringNumbersParsedAsString() {
        String data = """
                {"x": "2.75", "y": "-3.5", "r": "1.25"}
                """;

        ParseRequestBodyRequest json = new ParseRequestBodyRequest(data);

        ValidationRequest result = parser.parse(json);

        assertAll(
                () -> assertEquals("2.75", result.x()),
                () -> assertEquals("-3.5", result.y()),
                () -> assertEquals("1.25", result.r())
        );
    }

    @Test
    @DisplayName("Отсутствующее поле приводит к NullPointerException (obj.get(..) == null)")
    void parseMissingFieldThrowsNullPointerException() {
        String data = """
                {"x": 1.0, "y": 2.0}
                """;

        ParseRequestBodyRequest json = new ParseRequestBodyRequest(data);

        assertThrows(NullPointerException.class, () -> parser.parse(json));
    }

    @Test
    @DisplayName("Некорректный JSON приводит к JsonSyntaxException")
    void parseInvalidJsonThrowsJsonSyntaxException() {
        String data = "{ x: 1.0, y: 2.0, r: 3.0 ";

        ParseRequestBodyRequest json = new ParseRequestBodyRequest(data);

        assertThrows(JsonSyntaxException.class, () -> parser.parse(json));
    }
}
