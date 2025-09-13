import codec.implementations.json.JsonDecoder;
import enteties.request.records.DecodeRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JsonDecoderTest {

    private final JsonDecoder decoder = new JsonDecoder();

    @Test
    @DisplayName("Тест с положительными")
    void testDecodeWithPositiveValues() {
        DecodeRequest request = new DecodeRequest(1.5, 2.5, 3.5, true);
        String json = decoder.decode(request);

        String expected = """
                {
                    "x": 1.5,
                    "y": 2.5,
                    "r": 3.5,
                    "status": true,
                    "time": %s
                }
                """.formatted(LocalDate.now());

        assertEquals(expected, json);
    }

    @Test
    @DisplayName("Тест с отрицательными числами")
    void testDecodeWithNegativeValues() {
        DecodeRequest request = new DecodeRequest(-10, -20.5, -3, false);
        String json = decoder.decode(request);

        assertTrue(json.contains("\"x\": -10.0"));
        assertTrue(json.contains("\"y\": -20.5"));
        assertTrue(json.contains("\"r\": -3.0"));
        assertTrue(json.contains("\"status\": false"));
        assertTrue(json.contains("\"time\": " + LocalDate.now()));
    }

    @Test
    @DisplayName("Тест с нулями числами")
    void testDecodeWithZeroValues() {
        DecodeRequest request = new DecodeRequest(0, 0, 0, false);
        String json = decoder.decode(request);

        assertTrue(json.contains("\"x\": 0.0"));
        assertTrue(json.contains("\"y\": 0.0"));
        assertTrue(json.contains("\"r\": 0.0"));
        assertTrue(json.contains("\"status\": false"));
    }

    @Test
    @DisplayName("Тест вывода")
    void testDecodeOutputFormat() {
        DecodeRequest request = new DecodeRequest(0, 0, 0, true);
        String json = decoder.decode(request);

        assertTrue(json.trim().startsWith("{"));
        assertTrue(json.trim().endsWith("}"));
    }
}
