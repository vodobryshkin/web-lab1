package validation.abstractions;

import enteties.request.records.ValidationRequest;

/**
 * Абстрактный класс для упрощения создания конкретных обработчиков.
 */
public abstract class BaseHandler implements Handler {
    private Handler nextHandler;

    public BaseHandler() {
        nextHandler = null;
    }

    /**
     * Устанавливает следующего обработчика в цепочке ответственностей.
     *
     * @param handler следующий обработчик в цепочке ответственностей.
     */
    @Override
    public void setNext(Handler handler) {
        nextHandler = handler;
    }

    /**
     * Метод запускает обработку переданного запроса на валидацию следующим обработчиком.
     * Если следующий обработчик существует, то запускается его обработка. В противном случае, возвращается истина (так как
     * все нужные шаги были рассмотрены).
     *
     * @param validationRequest переданный запрос на валидацию.
     * @return статус выполнения обработки.
     */
    @Override
    public boolean handleNext(ValidationRequest validationRequest) {
        if (nextHandler != null) {
            return nextHandler.handle(validationRequest);
        }
        return true;
    }
}
