package entities.geometry.areas.factory.creators.implementations;

import entities.geometry.areas.factory.creators.interfaces.AreaCreator;
import entities.geometry.areas.implementations.RectangleArea;
import entities.geometry.areas.interfaces.Area;
import entities.geometry.model.Point;
import entities.request.implementations.areas.RectangleAreaRequest;
import entities.request.interfaces.AreaRequest;

import java.math.BigDecimal;

/**
 * Класс для автоматического создания прямоугольных областей.
 */
public class RectangleAreaCreator implements AreaCreator {
    /**
     * Метод для создания области по переданному запросу.
     * Параметры x, y идут в точку.
     *
     * @param areaRequest переданный запрос на создание прямоугольной области.
     * @param radius радиус из условия лабораторной.
     * @return созданную область.
     */
    @Override
    public Area createArea(AreaRequest areaRequest, BigDecimal radius) {
       if (areaRequest instanceof RectangleAreaRequest rectangleAreaRequest) {
           BigDecimal x = rectangleAreaRequest.x();
           BigDecimal y = rectangleAreaRequest.y();
           BigDecimal width = rectangleAreaRequest.widthK().multiply(radius);
           BigDecimal height = rectangleAreaRequest.heightK().multiply(radius);

           return new RectangleArea(new Point(x, y), width, height);
       }
       return null;
    }
}
