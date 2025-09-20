package codec.implementations.request;

import entities.request.implementations.messages.CheckoutRequest;
import entities.request.implementations.messages.ValidationRequest;
import entities.request.interfaces.NetworkRequest;
import ru.ifmo.se.gmt.geometry.model.Point;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * Класс для преобразования запроса на валидацию в запрос на проверку данных.
 * Нужен для преобразования данных для CheckoutManager, которое происходит в случае успешной валидации.
 */
@Slf4j
public class ValidationRequestToCheckoutRequestParser implements NetworkRequest {
    public CheckoutRequest parse(ValidationRequest validationRequest) {
        Point p = new Point(new BigDecimal(validationRequest.x()), new BigDecimal(validationRequest.y()));
        BigDecimal r = new BigDecimal(validationRequest.r());

        log.info("Created checkout request with fields: point=Point({}, {}), r={}", p.getX(), p.getY(), r);

        return new CheckoutRequest(p, r);
    }
}
