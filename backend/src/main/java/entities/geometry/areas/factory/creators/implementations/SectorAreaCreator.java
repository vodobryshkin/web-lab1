package entities.geometry.areas.factory.creators.implementations;

import entities.geometry.areas.factory.creators.interfaces.AreaCreator;
import entities.geometry.areas.implementations.SectorArea;
import entities.geometry.areas.interfaces.Area;
import entities.geometry.model.Point;
import entities.request.implementations.areas.SectorAreaRequest;
import entities.request.interfaces.AreaRequest;

import java.math.BigDecimal;

/**
 * Класс для автоматического создания круглых областей.
 */
public class SectorAreaCreator implements AreaCreator {
    /**
     * Метод для создания области по переданному запросу.
     *
     * @param areaRequest переданный запрос на создание области в виде сектора.
     * @param radius      радиус из условия лабораторной.
     * @return созданную область.
     */
    @Override
    public Area createArea(AreaRequest areaRequest, BigDecimal radius) {
        if (areaRequest instanceof SectorAreaRequest sectorAreaRequest) {
            BigDecimal xC = sectorAreaRequest.xC();
            BigDecimal yC = sectorAreaRequest.yC();
            BigDecimal radiusK = sectorAreaRequest.radiusK().multiply(radius);
            BigDecimal startAngleK = sectorAreaRequest.startAngleK().multiply(new BigDecimal(Math.PI));
            BigDecimal endAngleK = sectorAreaRequest.endAngleK().multiply(new BigDecimal(Math.PI));

            return new SectorArea(new Point(xC, yC), radiusK, startAngleK, endAngleK);
        }
        return null;
    }
}
