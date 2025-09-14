package entities.geometry.areas.factory.creators.interfaces;

import entities.geometry.areas.interfaces.Area;
import entities.request.interfaces.AreaRequest;

import java.math.BigDecimal;

/**
 * Интерфейс для определения функциональности конкретных создателей областей.
 */
public interface AreaCreator {
    /**
     * Метод для создания области по переданному запросу.
     *
     * @param areaRequest переданный запрос на создание области.
     * @param radius радиус из условия лабораторной.
     * @return созданную область.
     */
    Area createArea(AreaRequest areaRequest, BigDecimal radius);
}
