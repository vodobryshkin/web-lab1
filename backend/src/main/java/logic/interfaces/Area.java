package logic.interfaces;


import enteties.model.Point;

/**
 * Интерфейс для определения функциональности конкретных областей на координатной плоскости.
 */
public interface Area {
    /**
     * Метод для проверки вхождения точки внутрь заданной области.
     *
     * @param point точка для проверки.
     * @return информацию входит ли точка в область (true) или нет (false).
     */
    boolean checkPoint(Point point);
}
