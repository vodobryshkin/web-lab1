package entities.request.implementations.network;

import entities.request.interfaces.NetworkRequest;

/**
 * Класс для реализации запроса на парсинг тела принятого http-запроса.
 *
 * @param requestBody тело запроса.
 */
public record ParseRequestBodyRequest(String requestBody) implements NetworkRequest {}
