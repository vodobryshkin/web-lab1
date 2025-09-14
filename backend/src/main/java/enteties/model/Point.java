package enteties.model;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * Класс-абстракция над точкой на координатной плоскости.
 * Нужен для обработки данных на бэкенде.
 */
@Getter
public class Point {
    private final BigDecimal x;
    private final BigDecimal y;

    /**
     * Конструктор класса Point
     *
     * @param x значение по оси x.
     * @param y значение по оси y.
     */
    public Point(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Point(%f, %f)", x, y);
    }
}
