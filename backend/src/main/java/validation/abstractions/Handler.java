package validation.abstractions;

import entities.request.implementations.messages.ValidationRequest;

/**
 * Интерфейс для определения функциональности конкретных обработчиков.
 */
public interface Handler {
    /**
     * Метод выполняет обработку переданного запроса на валидацию.
     *
     * @param request переданный запрос на валидацию.
     * @return статус выполнения обработки.
     */
    boolean handle(ValidationRequest request);

    /**
     * Устанавливает следующего обработчика в цепочке ответственностей.
     *
     * @param handler следующий обработчик в цепочке ответственностей.
     */
    void setNext(Handler handler);

    /**
     * Метод запускает обработку переданного запроса на валидацию следующим обработчиком.
     *
     * @param request переданный запрос на валидацию.
     * @return статус выполнения обработки.
     */
    boolean handleNext(ValidationRequest request);
}
