package codec.interfaces;

import enteties.request.records.ValidationRequest;

/**
 * Интерфейс для определения функциональности конкретных парсеров.
 */
public interface Parser {
    /**
     * Функция дря парсинга переданных данных.
     *
     * @param data переданные данные для парсинга.
     * @return сформированный после парсинга запрос на валидацию.
     */
    ValidationRequest parse(String data);
}
