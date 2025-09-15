import codec.implementations.json.coordinates.JsonCoordinatesDecoder;
import entities.request.implementations.messages.DecodeRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JsonCoordinatesDecoderTest {

    private final JsonCoordinatesDecoder decoder = new JsonCoordinatesDecoder();

    @Test
    @DisplayName("Тест с положительными")
    void testDecodeWithPositiveValues() {
        BigDecimal x = new BigDecimal("1.5");
        BigDecimal y = new BigDecimal("2.5");
        BigDecimal r = new BigDecimal("3.5");

        DecodeRequest request = new DecodeRequest(x, y, r, true);
        String json = decoder.decode(request);

        String expected = """
                {
                    "x": 1.5,
                    "y": 2.5,
                    "r": 3.5,
                    "status": true,
                    "time": "%s"
                }
                """.formatted(LocalDate.now().toString());

        assertEquals(expected, json);
    }

    @Test
    @DisplayName("Тест с отрицательными числами")
    void testDecodeWithNegativeValues() {
        BigDecimal x = new BigDecimal("-10");
        BigDecimal y = new BigDecimal("-20.5");
        BigDecimal r = new BigDecimal("-3");

        DecodeRequest request = new DecodeRequest(x, y, r, false);
        String json = decoder.decode(request);

        System.out.println(json);

        assertTrue(json.contains("\"x\": -10"));
        assertTrue(json.contains("\"y\": -20.5"));
        assertTrue(json.contains("\"r\": -3"));
        assertTrue(json.contains("\"status\": false"));
        assertTrue(json.contains("\"time\": \"" + LocalDate.now() + "\""));
    }

    @Test
    @DisplayName("Тест с нулями числами")
    void testDecodeWithZeroValues() {
        BigDecimal x = new BigDecimal("0");
        BigDecimal y = new BigDecimal("0");
        BigDecimal r = new BigDecimal("0");

        DecodeRequest request = new DecodeRequest(x, y, r, false);
        String json = decoder.decode(request);

        assertTrue(json.contains("\"x\": 0"));
        assertTrue(json.contains("\"y\": 0"));
        assertTrue(json.contains("\"r\": 0"));
        assertTrue(json.contains("\"status\": false"));
    }

    @Test
    @DisplayName("Тест вывода")
    void testDecodeOutputFormat() {
        BigDecimal x = new BigDecimal("0");
        BigDecimal y = new BigDecimal("0");
        BigDecimal r = new BigDecimal("0");

        DecodeRequest request = new DecodeRequest(x, y, r, true);
        String json = decoder.decode(request);

        assertTrue(json.trim().startsWith("{"));
        assertTrue(json.trim().endsWith("}"));
    }
}
