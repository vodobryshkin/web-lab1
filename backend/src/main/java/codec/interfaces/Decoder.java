package codec.interfaces;

import entities.request.implementations.messages.DecodeRequest;

/**
 * Интерфейс для определения функциональности конкретных депарсеров.
 */
public interface Decoder {
    /**
     * Метод для сериализации переданных данных.
     *
     * @param data переданные данные для сериализации.
     * @return сформированный после бизнес-логики запрос на сериализацию.
     */
    String decode(DecodeRequest data);
}
