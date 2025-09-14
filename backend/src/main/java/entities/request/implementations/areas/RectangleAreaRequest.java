package entities.request.implementations.areas;

import entities.request.interfaces.AreaRequest;

import java.math.BigDecimal;

/**
 * Класс для реализации запроса на создание прямоугольной области.
 *
 * @param x координата по x для левой нижней точки прямоугольника.
 * @param y координата по y для левой нижней точки прямоугольника.
 * @param widthK скаляр для ширины (на сколько нужно умножить радиус, чтобы получить ширину).
 * @param heightK скаляр для длины (на сколько нужно умножить радиус, чтобы получить длину).
 */
public record RectangleAreaRequest(BigDecimal x, BigDecimal y, BigDecimal widthK, BigDecimal heightK)
        implements AreaRequest {}
