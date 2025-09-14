package codec.interfaces;

import entities.request.interfaces.Request;

import java.io.IOException;

/**
 * Интерфейс для определения функциональности конкретных парсеров.
 */
public interface Parser {
    /**
     * Метод для парсинга переданных данных.
     *
     * @param data переданные данные для парсинга.
     * @return сформированный после парсинга запрос.
     */
    Request parse(String data) throws IOException;
}
