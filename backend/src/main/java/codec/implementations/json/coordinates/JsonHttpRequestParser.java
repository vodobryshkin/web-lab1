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
                JsonObject obj = gson.fromJson(parseRequestBodyRequest.requestBody(), JsonObject.class);

                boolean isDataAdderRequest = obj.get("sendDataResponse").getAsBoolean();

                if (isDataAdderRequest) {
                    String x = obj.get("x").getAsString();
                    String y = obj.get("y").getAsString();
                    String r = obj.get("r").getAsString();

                    log.info("Created validation request with fields: x={}, y={}, r={}.", x, y, r);

                    return new ValidationRequest(x, y, r);
                }

                String requestType = obj.get("minioRequestType").getAsString();

                if (requestType.equals("get")) {
                    return new MinioGetRequest();
                }

                throw new Exception();

            } catch (Exception e) {
                log.error("Could not parse a JSON.");
            }

        }

        return null;
    }
}
