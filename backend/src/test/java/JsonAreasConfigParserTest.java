import codec.implementations.json.config.JsonAreasConfigParser;
import entities.request.implementations.areas.RectangleAreaRequest;
import entities.request.implementations.areas.SectorAreaRequest;
import entities.request.implementations.areas.TriangleAreaRequest;
import entities.request.implementations.messages.AreasRequest;
import entities.request.interfaces.AreaRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonAreasConfigParserTest {
    @Test
    @DisplayName("Тест из варианта")
    void VariantTest() throws IOException {
        JsonAreasConfigParser jsonAreasConfigParser = new JsonAreasConfigParser();
        AreasRequest areasRequestFromParser = jsonAreasConfigParser.parse("/home/vodobryshkin/progs/proj/IdeaProjects/web-lab1/backend/src/main/resources/areas.json");

        AreaRequest r = new RectangleAreaRequest(new BigDecimal("0"), new BigDecimal("0"),
                new BigDecimal("0.5"), new BigDecimal("1"));
        AreaRequest t = new TriangleAreaRequest(new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("1"),
                new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("-1"));
        AreaRequest c = new SectorAreaRequest(new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0.5")
                , new BigDecimal("1"), new BigDecimal("1.5"));

        List<AreaRequest> areaRequestList = new ArrayList<>();

        areaRequestList.add(r);
        areaRequestList.add(t);
        areaRequestList.add(c);

        boolean status = true;

        for (int i = 0; i < areaRequestList.size(); i++) {
            if (!areaRequestList.get(i).equals(areasRequestFromParser.areaRequests().get(i))) {
                System.out.println();
                status = false;
                break;
            }
        }

        assertTrue(status);
    }
}
