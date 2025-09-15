package entities.request.implementations.messages;

import entities.request.interfaces.Request;

/**
 * Класс с данными для отправки на валидацию.
 * Получается в результате обработки поступивших данных парсером.
 *
 * @param x потенциальная координата по оси x.
 * @param y потенциальная координата по оси y.
 * @param r потенциальный радиус на координатной плоскости.
 */
public record ValidationRequest(String x, String y, String r) implements Request {}
