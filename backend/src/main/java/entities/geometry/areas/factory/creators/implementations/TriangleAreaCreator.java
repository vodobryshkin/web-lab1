package entities.geometry.areas.factory.creators.implementations;

import entities.geometry.areas.factory.creators.interfaces.AreaCreator;
import entities.geometry.areas.implementations.TriangleArea;
import entities.geometry.areas.interfaces.Area;
import entities.geometry.model.Point;
import entities.request.implementations.areas.TriangleAreaRequest;
import entities.request.interfaces.AreaRequest;

import java.math.BigDecimal;

/**
 * Класс для автоматического создания треугольных областей.
 */
public class TriangleAreaCreator  implements AreaCreator {
    /**
     * Метод для создания области по переданному запросу.
     * Параметры x, y идут в точку.
     *
     * @param areaRequest переданный запрос на создание треугольной области.
     * @param radius радиус из условия лабораторной.
     * @return созданную область.
     */
    @Override
    public Area createArea(AreaRequest areaRequest, BigDecimal radius) {
        if (areaRequest instanceof TriangleAreaRequest triangleAreaRequest) {
            BigDecimal xA = triangleAreaRequest.xA();
            BigDecimal yA = triangleAreaRequest.yA();

            BigDecimal xB = triangleAreaRequest.xBK().multiply(radius);
            BigDecimal yB = triangleAreaRequest.yBK().multiply(radius);

            BigDecimal xC = triangleAreaRequest.xCK().multiply(radius);
            BigDecimal yC = triangleAreaRequest.yCK().multiply(radius);

            return new TriangleArea(new Point(xA, yA), new Point(xB, yB), new Point(xC, yC));
        }
        return null;
    }
}
