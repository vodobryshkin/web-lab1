package codec.implementations.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import enteties.request.records.ValidationRequest;
import codec.interfaces.Parser;

/**
 * Класс для парсинга JSON.
 */
public class JsonParser implements Parser {
    /**
     * Функция для парсинга переданных данных в формате JSON.
     * Для парсинга используется библиотека GSON.
     *
     * @param data переданные данные для парсинга.
     * @return сформированный после парсинга запрос на валидацию.
     */
    @Override
    public ValidationRequest parse(String data) {
        Gson gson = new Gson();

        JsonObject obj = gson.fromJson(data, JsonObject.class);

        Double x = obj.get("x").getAsDouble();
        Double y = obj.get("y").getAsDouble();
        Double r = obj.get("r").getAsDouble();

        return new ValidationRequest(x, y, r);
    }
}
