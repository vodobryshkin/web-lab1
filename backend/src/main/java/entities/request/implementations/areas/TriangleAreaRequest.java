package entities.request.implementations.areas;

import entities.request.interfaces.AreaRequest;

import java.math.BigDecimal;

/**
 * Класс для реализации запроса на создание треугольной области.
 *
 * @param xA параметр x для точки A.
 * @param yA параметр y для точки A.
 * @param xBK скаляр для параметра x точки B.
 * @param yBK скаляр для параметра y точки B.
 * @param xCK скаляр для параметра x точки C.
 * @param yCK скаляр для параметра y точки C.
 */
public record TriangleAreaRequest(BigDecimal xA, BigDecimal yA, BigDecimal xBK, BigDecimal yBK, BigDecimal xCK,
                                  BigDecimal yCK) implements AreaRequest {}
