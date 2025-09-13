package validation.handlers;

import enteties.request.records.ValidationRequest;
import validation.abstractions.BaseHandler;

/**
 * Класс для обработки валидатором значения поступившего R.
 */
public class RHandler extends BaseHandler {
    public RHandler() {
        super();
    }

    /**
     * Метод выполняет обработку значения R из переданного запроса на валидацию.
     * Если метод удовлетворяет условию, то происходит переход на следующий обработчик.
     * В противном случае, возвращается false (завершилось неправильно).
     *
     * @param request переданный запрос на валидацию.
     * @return статус выполнения обработки.
     */
    @Override
    public boolean handle(ValidationRequest request) {
        if (request.r() >= 2 && request.r() <= 5) {
            return handleNext(request);
        }

        return false;
    }
}
