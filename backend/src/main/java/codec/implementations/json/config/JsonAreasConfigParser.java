package codec.implementations.json.config;

import codec.interfaces.Parser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entities.request.implementations.areas.RectangleAreaRequest;
import entities.request.implementations.areas.SectorAreaRequest;
import entities.request.implementations.areas.TriangleAreaRequest;
import entities.request.implementations.messages.AreasRequest;
import entities.request.interfaces.AreaRequest;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для парсинга конфигурации к создаваемым областям.
 */
public class JsonAreasConfigParser implements Parser {
    /**
     * Функция для парсинга переданных данных.
     *
     * @param configName название файла переданной конфигурации.
     * @return сформированный после парсинга запрос на валидацию.
     */
    @Override
    public AreasRequest parse(String configName) throws IOException {
        String json = Files.readString(Path.of(configName));

        List<AreaRequest> areaRequests = new ArrayList<>();

        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(json, JsonObject.class);

        List<JsonElement> listOfAreas = obj.get("areas").getAsJsonArray().asList();

        for (JsonElement jsonElement: listOfAreas) {
            JsonObject areaInfo = jsonElement.getAsJsonObject();

            String type = areaInfo.get("type").getAsString();

            switch (type) {
                case "rectangle":
                    BigDecimal x = areaInfo.get("x").getAsBigDecimal();
                    BigDecimal y = areaInfo.get("y").getAsBigDecimal();
                    BigDecimal widthK = areaInfo.get("widthK").getAsBigDecimal();
                    BigDecimal heightK = areaInfo.get("heightK").getAsBigDecimal();

                    areaRequests.add(new RectangleAreaRequest(x, y, widthK, heightK));
                    break;

                case "triangle":
                    BigDecimal xA = areaInfo.get("xA").getAsBigDecimal();
                    BigDecimal yA = areaInfo.get("yA").getAsBigDecimal();
                    BigDecimal xBK = areaInfo.get("xBK").getAsBigDecimal();
                    BigDecimal yBK = areaInfo.get("yBK").getAsBigDecimal();
                    BigDecimal xCK = areaInfo.get("xCK").getAsBigDecimal();
                    BigDecimal yCK = areaInfo.get("yCK").getAsBigDecimal();

                    areaRequests.add(new TriangleAreaRequest(xA, yA, xBK, yBK, xCK, yCK));
                    break;

                case "circle":
                    BigDecimal xC = areaInfo.get("xC").getAsBigDecimal();
                    BigDecimal yC = areaInfo.get("yC").getAsBigDecimal();
                    BigDecimal radiusK = areaInfo.get("radiusK").getAsBigDecimal();
                    BigDecimal startAngleK = areaInfo.get("startAngleK").getAsBigDecimal();
                    BigDecimal endAngleK = areaInfo.get("endAngleK").getAsBigDecimal();

                    areaRequests.add(new SectorAreaRequest(xC, yC, radiusK, startAngleK, endAngleK));
                    break;
            }
        }

        return new AreasRequest(areaRequests);
    }
}
