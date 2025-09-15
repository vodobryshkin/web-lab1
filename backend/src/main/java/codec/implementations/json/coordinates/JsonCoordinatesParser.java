package codec.implementations.json.coordinates;

import codec.interfaces.RequestParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entities.request.implementations.messages.ValidationRequest;
import entities.request.implementations.network.ParseRequestBodyRequest;
import entities.request.interfaces.Request;

import java.math.BigDecimal;

/**
 * Класс для парсинга JSON.
 */
public class JsonCoordinatesParser implements RequestParser {
    /**
     * Метод для парсинга переданных данных в формате JSON.
     * Для парсинга используется библиотека GSON.
     *
     * @param request переданные данные для парсинга.
     * @return сформированный после парсинга запрос на валидацию.
     */
    @Override
    public ValidationRequest parse(Request request) {
        Gson gson = new Gson();

        if (request instanceof ParseRequestBodyRequest parseRequestBodyRequest) {
            JsonObject obj = gson.fromJson(parseRequestBodyRequest.requestBody(), JsonObject.class);

            BigDecimal x = obj.get("x").getAsBigDecimal();
            BigDecimal y = obj.get("y").getAsBigDecimal();
            BigDecimal r = obj.get("r").getAsBigDecimal();

            return new ValidationRequest(x, y, r);
        }

        return null;
    }
}
