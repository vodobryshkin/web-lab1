package validation.managers;

import entities.request.implementations.messages.ValidationRequest;
import lombok.Getter;
import lombok.Setter;
import validation.abstractions.Handler;
import validation.handlers.RHandler;
import validation.handlers.XHandler;
import validation.handlers.YHandler;

/**
 * Класс для валидации поступивших значений.
 */
public class ValidationManager {
    @Getter
    @Setter
    private Handler startHandler;

    /**
     * Конструктор класса ValidationManager.
     * В нём создаются все необходимые обработчики и строится цепочка ответственностей.
     */
    public ValidationManager() {
        XHandler xHandler = new XHandler();
        YHandler yHandler = new YHandler();
        RHandler rHandler = new RHandler();

        xHandler.setNext(yHandler);
        yHandler.setNext(rHandler);

        setStartHandler(xHandler);
    }

    /**
     * Метод для валидации поступившего запроса на валидацию по цепочке ответственностей.
     *
     * @param request поступивший запрос на валидацию.
     * @return ответ
     */
    public boolean validate(ValidationRequest request) {
        return getStartHandler().handle(request);
    }
}
