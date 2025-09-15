package codec.implementations.request;

import entities.request.implementations.messages.CheckoutRequest;
import entities.request.implementations.messages.DecodeRequest;

import java.math.BigDecimal;

/**
 * Класс для создания запроса на перекодировку из запроса на проверку.
 */
public class DecodeRequestDecoder {
    /**
     * Метод для создания запроса на десереализацию.
     *
     * @param checkoutRequest запрос на проверку.
     * @param status результат проверки бизнес-логикой.
     * @return сформированный запрос на десереализацию.
     */
    public DecodeRequest decode(CheckoutRequest checkoutRequest, boolean status) {
        BigDecimal x = checkoutRequest.point().getX();
        BigDecimal y = checkoutRequest.point().getY();
        BigDecimal r = checkoutRequest.r();

        return new DecodeRequest(x, y, r, status);
    }
}
