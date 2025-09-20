package codec.interfaces;

import entities.request.interfaces.Request;
import ru.ifmo.se.validation.request.ValidationRequest;

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
    ValidationRequest parse(Request request) throws IOException;
}
