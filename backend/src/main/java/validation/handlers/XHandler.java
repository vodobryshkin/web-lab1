package validation.handlers;

import enteties.request.records.ValidationRequest;
import validation.abstractions.BaseHandler;

import java.math.BigDecimal;

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
        BigDecimal x = request.x();

        if (x.compareTo(BigDecimal.valueOf(-5)) >= 0
                && x.compareTo(BigDecimal.valueOf(3)) <= 0) {
            return handleNext(request);
        }

        return false;
    }
}
