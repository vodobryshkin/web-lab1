package codec.implementations.json.coordinates;

import codec.interfaces.RequestParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entities.request.implementations.minio.MinioGetRequest;
import entities.request.implementations.network.ParseRequestBodyRequest;
import entities.request.interfaces.Request;
import lombok.extern.slf4j.Slf4j;
import ru.ifmo.se.validation.request.ValidationManagerOutRequest;
import ru.ifmo.se.validation.request.ValidationRequest;

/**
 * Класс для парсинга JSON.
 */
@Slf4j
public class JsonHttpRequestParser implements RequestParser {
    /**
     * Метод для парсинга переданных данных в формате JSON.
     * Для парсинга используется библиотека GSON.
     *
     * @param request переданные данные для парсинга.
     * @return сформированный после парсинга запрос на валидацию.
     */
    @Override
    public ValidationManagerOutRequest parse(Request request) {
        Gson gson = new Gson();

        if (request instanceof ParseRequestBodyRequest parseRequestBodyRequest) {
            try {
                if (parseRequestBodyRequest.method().equals("GET")) {
                    if (parseRequestBodyRequest.requestBody().equals("sendDataResponse=false")) {
                        return new MinioGetRequest();
                    }

                    throw new Exception();
                }
                JsonObject obj = gson.fromJson(parseRequestBodyRequest.requestBody(), JsonObject.class);

                String x = obj.get("x").getAsString();
                String y = obj.get("y").getAsString();
                String r = obj.get("r").getAsString();

                log.info("Created validation request with fields: x={}, y={}, r={}.", x, y, r);

                return new ValidationRequest(x, y, r);

            } catch (Exception e) {
                log.error("Could not parse a JSON.");
            }

        }

        return null;
    }
}
