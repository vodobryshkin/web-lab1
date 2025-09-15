package codec.interfaces;

import entities.request.interfaces.Request;

import java.io.IOException;

/**
 * Интерфейс для определения функциональности конкретных парсеров запросов.
 */
public interface RequestParser {
    /**
     * Метод для парсинга переданных данных.
     *
     * @param request переданный запрос для парсинга.
     * @return сформированный после парсинга запрос.
     */
    Request parse(Request request) throws IOException;
}
