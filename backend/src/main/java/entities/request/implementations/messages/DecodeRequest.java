package entities.request.implementations.messages;

import entities.request.interfaces.Request;

import java.math.BigDecimal;

/**
 * Класс с данными для отправки на десериализацию в JSON.
 * Получается в результате обработки поступивших данных бизнес-логикой.
 *
 * @param x координата по оси x.
 * @param y координата по оси y.
 * @param r радиус на координатной плоскости.
 * @param status результат обработки бизнес-логикой.
 */
public record DecodeRequest(BigDecimal x, BigDecimal y, BigDecimal r, boolean status) implements Request {}
