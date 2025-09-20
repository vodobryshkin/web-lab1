package entities.request.implementations.messages;

import entities.request.interfaces.Request;
import geometry.model.Point;

import java.math.BigDecimal;

/**
 * Класс с данными для отправки на проверки на бизнес-логику.
 * Получается в результате обработки поступивших данных валидатором.
 *
 * @param  point поступившая на координатную плоскость точка.
 * @param r радиус на координатной плоскости.
 */
public record CheckoutRequest(Point point, BigDecimal r) implements Request {}
