import enteties.model.Point;
import logic.implementations.RectangleArea;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectangleAreaTest {
    @Test
    @DisplayName("Точка внутри области")
    void pointInsideAreaTest() {
        BigDecimal x0 = new BigDecimal("2");
        BigDecimal y0 = new BigDecimal("2");

        BigDecimal x = new BigDecimal("0");
        BigDecimal y = new BigDecimal("0");

        BigDecimal height = new BigDecimal("4");
        BigDecimal width = new BigDecimal("4");

        Point p = new Point(x0, y0);
        RectangleArea rectangleArea = new RectangleArea(new Point(x, y), width, height);
        assertTrue(rectangleArea.checkPoint(p));
    }

    @Test
    @DisplayName("Точка на границе по оси ординат")
    void pointOnYBorder() {
        BigDecimal x0 = new BigDecimal("2");
        BigDecimal y0 = new BigDecimal("0");

        BigDecimal x = new BigDecimal("0");
        BigDecimal y = new BigDecimal("0");

        BigDecimal height = new BigDecimal("4");
        BigDecimal width = new BigDecimal("4");

        Point p = new Point(x0, y0);
        RectangleArea rectangleArea = new RectangleArea(new Point(x, y), width, height);
        assertTrue(rectangleArea.checkPoint(p));
    }

    @Test
    @DisplayName("Точка на границе по оси абсцисс")
    void pointOnXBorder() {
        BigDecimal x0 = new BigDecimal("0");
        BigDecimal y0 = new BigDecimal("2");

        BigDecimal x = new BigDecimal("0");
        BigDecimal y = new BigDecimal("0");

        BigDecimal height = new BigDecimal("4");
        BigDecimal width = new BigDecimal("4");

        Point p = new Point(x0, y0);
        RectangleArea rectangleArea = new RectangleArea(new Point(x, y), width, height);
        assertTrue(rectangleArea.checkPoint(p));
    }

    @Test
    @DisplayName("Точка на граничной точке")
    void pointOnLeftLowerPoint() {
        BigDecimal x0 = new BigDecimal("0");
        BigDecimal y0 = new BigDecimal("0");

        BigDecimal x = new BigDecimal("0");
        BigDecimal y = new BigDecimal("0");

        BigDecimal height = new BigDecimal("4");
        BigDecimal width = new BigDecimal("4");

        Point p = new Point(x0, y0);
        RectangleArea rectangleArea = new RectangleArea(new Point(x, y), width, height);
        assertTrue(rectangleArea.checkPoint(p));
    }

    @Test
    @DisplayName("Точка за границей области")
    void pointOutsideArea() {
        BigDecimal x0 = new BigDecimal("-1");
        BigDecimal y0 = new BigDecimal("-1");

        BigDecimal x = new BigDecimal("0");
        BigDecimal y = new BigDecimal("0");

        BigDecimal height = new BigDecimal("4");
        BigDecimal width = new BigDecimal("4");

        Point p = new Point(x0, y0);
        RectangleArea rectangleArea = new RectangleArea(new Point(x, y), width, height);
        assertFalse(rectangleArea.checkPoint(p));
    }
}
