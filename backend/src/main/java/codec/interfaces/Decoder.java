package codec.interfaces;

import enteties.request.records.DecodeRequest;

/**
 * Интерфейс для определения функциональности конкретных депарсеров.
 */
public interface Decoder {
    /**
     * Функция дря сериализации переданных данных.
     *
     * @param data переданные данные для сериализации.
     * @return сформированный после бизнес-логики запрос на сериализацию.
     */
    String decode(DecodeRequest data);
}
