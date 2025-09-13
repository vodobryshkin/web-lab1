package validation.handlers;

import enteties.request.records.ValidationRequest;
import validation.abstractions.BaseHandler;

/**
 * Класс для обработки валидатором значения поступившего Y.
 */
public class YHandler extends BaseHandler {
    public YHandler() {
        super();
    }

    /**
     * Метод выполняет обработку значения Y из переданного запроса на валидацию.
     * Если метод удовлетворяет условию, то происходит переход на следующий обработчик.
     * В противном случае, возвращается false (завершилось неправильно).
     *
     * @param request переданный запрос на валидацию.
     * @return статус выполнения обработки.
     */
    @Override
    public boolean handle(ValidationRequest request) {
        if (request.y() >= -3 && request.y() <= 3) {
            return handleNext(request);
        }

        return false;
    }
}
