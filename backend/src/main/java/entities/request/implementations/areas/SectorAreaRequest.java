package entities.request.implementations.areas;

import entities.request.interfaces.AreaRequest;

import java.math.BigDecimal;

/**
 * Класс для реализации запроса на создание области в виде сектора.
 *
 * @param xC параметр x для центральной точки окружности.
 * @param yC параметр y для центральной точки окружности.
 * @param radiusK скаляр для радиуса окружности (на сколько нужно умножить радиус, чтобы получить радиус окружности).
 * @param startAngleK скаляр для начального угла (на сколько нужно умножить пи, чтобы получить начальный угол).
 * @param endAngleK скаляр для конечного угла (на сколько нужно умножить пи, чтобы получить конечный угол).
 */
public record SectorAreaRequest(BigDecimal xC, BigDecimal yC, BigDecimal radiusK, BigDecimal startAngleK,
                                BigDecimal endAngleK) implements AreaRequest {}
