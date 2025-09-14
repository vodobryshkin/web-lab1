package logic.implementations;

import enteties.model.Point;
import logic.interfaces.Area;

import java.math.BigDecimal;

/**
 * Класс, реализующий прямоугольную область.
 */
public class RectangleArea implements Area {
    private final Point leftLowerPoint;
    private final BigDecimal height;
    private final BigDecimal width;

    /**
     * Конструктор класса прямоугольной области.
     * Задаёт прямоугольную область по переданным параметрам.
     *
     * @param point точка, соответсвующая левому нижнему углу прямоугольника.
     * @param height высота прямоугольника (величина по оси y).
     * @param width ширина прямоугольника (величина по оси x).
     */
    public RectangleArea(Point point, BigDecimal width, BigDecimal height) {
        this.leftLowerPoint = point;
        this.height = height;
        this.width = width;
    }

    /**
     * Метод для проверки вхождения точки внутрь заданной прямоугольной области.
     * Чтобы точка лежала внутри прямоугольной области, нужно чтобы она находилась между всеми её границами.
     *
     * @param point точка для проверки.
     * @return информацию входит ли точка в область (true) или нет (false).
     */
    @Override
    public boolean checkPoint(Point point) {
        BigDecimal x = leftLowerPoint.getX();
        BigDecimal y = leftLowerPoint.getY();

        BigDecimal x0 = point.getX();
        BigDecimal y0 = point.getY();

        return (x0.compareTo(x) >= 0 && x0.compareTo(x.add(width)) <= 0 &&
                y0.compareTo(y) >= 0 && y0.compareTo(y.add(height)) <= 0);
    }
}
