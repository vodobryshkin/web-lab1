package codec.implementations.json.coordinates;

import codec.interfaces.Decoder;
import entities.request.implementations.messages.DecodeRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс для перекодировки из запроса на десериализацию в JSON.
 */
@Slf4j
public class JsonCoordinatesDecoder implements Decoder {

    /**
     * Метод для сериализации в JSON переданных данных.
     *
     * @param data переданные данные для сериализации в JSON.
     * @return сформированный после бизнес-логики запрос на сериализацию в JSON.
     */
    @Override
    public String decode(DecodeRequest data) {
        log.info("Successfully created JSON response body.");
        return """
                {
                    "x": %s,
                    "y": "%s",
                    "r": "%s",
                    "status": %s
                }
                """.formatted(data.x(), data.y(), data.r(), data.status());
    }
}
