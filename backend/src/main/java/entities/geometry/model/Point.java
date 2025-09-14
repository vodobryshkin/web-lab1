package entities.geometry.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o instanceof Point point1) {
            return this.x.equals(point1.x) && Objects.equals(y, point1.y);
        }
        return false;
    }
}
