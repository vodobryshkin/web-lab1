package enteties.model;

import lombok.Getter;

/**
 * Класс-абстракция над точкой на координатной плоскости.
 * Нужен для обработки данных на бэкенде.
 */
@Getter
public class Point {
    private final double x;
    private final double y;

    /**
     * Конструктор класса Point
     *
     * @param x значение по оси x.
     * @param y значение по оси y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Point(%f, %f)", x, y);
    }
}
