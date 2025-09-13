package validation.handlers;

import enteties.request.records.ValidationRequest;
import validation.abstractions.BaseHandler;

/**
 * Класс для обработки валидатором значения поступившего X.
 */
public class XHandler extends BaseHandler {
    public XHandler() {
        super();
    }

    /**
     * Метод выполняет обработку значения X из переданного запроса на валидацию.
     * Если метод удовлетворяет условию, то происходит переход на следующий обработчик.
     * В противном случае, возвращается false (завершилось неправильно).
     *
     * @param request переданный запрос на валидацию.
     * @return статус выполнения обработки.
     */
    @Override
    public boolean handle(ValidationRequest request) {
        if (request.x() >= -5 && request.x() <= 3) {
            return handleNext(request);
        }

        return false;
    }
}
