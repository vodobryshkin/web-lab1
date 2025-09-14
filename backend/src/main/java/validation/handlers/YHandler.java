package validation.handlers;

import enteties.request.records.ValidationRequest;
import validation.abstractions.BaseHandler;

import java.math.BigDecimal;

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
        BigDecimal y = request.y();

        if (y.compareTo(BigDecimal.valueOf(-3)) >= 0
                && y.compareTo(BigDecimal.valueOf(3)) <= 0) {
            return handleNext(request);
        }

        return false;
    }
}
