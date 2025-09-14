package codec.implementations.json.coordinates;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entities.request.implementations.messages.ValidationRequest;
import codec.interfaces.Parser;

import java.math.BigDecimal;

/**
 * Класс для парсинга JSON.
 */
public class JsonCoordinatesParser implements Parser {
    /**
     * Метод для парсинга переданных данных в формате JSON.
     * Для парсинга используется библиотека GSON.
     *
     * @param data переданные данные для парсинга.
     * @return сформированный после парсинга запрос на валидацию.
     */
    @Override
    public ValidationRequest parse(String data) {
        Gson gson = new Gson();

        JsonObject obj = gson.fromJson(data, JsonObject.class);

        BigDecimal x = obj.get("x").getAsBigDecimal();
        BigDecimal y = obj.get("y").getAsBigDecimal();
        BigDecimal r = obj.get("r").getAsBigDecimal();

        return new ValidationRequest(x, y, r);
    }
}
