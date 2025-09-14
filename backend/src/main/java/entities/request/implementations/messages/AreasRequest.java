package entities.request.implementations.messages;

import entities.request.interfaces.AreaRequest;
import entities.request.interfaces.Request;

import java.util.List;

/**
 * Класс для реализации запроса с распарсенными запросами.
 *
 * @param areaRequests список с распарсенными запросами.
 */
public record AreasRequest(List<AreaRequest> areaRequests) implements Request {}
