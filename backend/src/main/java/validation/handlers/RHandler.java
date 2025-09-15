package validation.handlers;

import entities.request.implementations.messages.ValidationRequest;
import validation.abstractions.BaseHandler;

import java.math.BigDecimal;

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
        try {
            BigDecimal r = new BigDecimal(request.r());

            if (r.compareTo(BigDecimal.valueOf(2)) >= 0
                    && r.compareTo(BigDecimal.valueOf(5)) <= 0) {
                return handleNext(request);
            }

            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
