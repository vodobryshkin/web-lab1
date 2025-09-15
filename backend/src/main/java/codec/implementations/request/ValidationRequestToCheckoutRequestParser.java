package codec.implementations.request;

import entities.geometry.model.Point;
import entities.request.implementations.messages.CheckoutRequest;
import entities.request.implementations.messages.ValidationRequest;

import java.math.BigDecimal;

/**
 * Класс для преобразования запроса на валидацию в запрос на проверку данных.
 * Нужен для преобразования данных для CheckoutManager, которое происходит в случае успешной валидации.
 */
public class ValidationRequestToCheckoutRequestParser {
    public CheckoutRequest parse(ValidationRequest validationRequest) {
        Point p = new Point(validationRequest.x(), validationRequest.y());
        BigDecimal r = validationRequest.r();
        return new CheckoutRequest(p, r);
    }
}
