package network;

import codec.implementations.json.coordinates.JsonCoordinatesDecoder;
import codec.implementations.json.coordinates.JsonHttpRequestParser;
import codec.implementations.request.DecodeRequestDecoder;
import codec.implementations.request.ValidationRequestToCheckoutRequestParser;
import com.fastcgi.FCGIInterface;
import entities.request.implementations.messages.CheckoutRequest;
import entities.request.implementations.messages.DecodeRequest;
import entities.request.implementations.network.ParseRequestBodyRequest;
import http.minio.client.MinioHttpClient;
import http.status.HttpResponseCode;
import logic.CheckoutManager;
import lombok.extern.slf4j.Slf4j;
import network.managers.FcgiRequestBodyReader;
import network.managers.HttpResponseSender;
import ru.ifmo.se.validation.managers.ValidationManager;
import ru.ifmo.se.validation.request.ValidationManagerOutRequest;
import ru.ifmo.se.validation.request.ValidationRequest;

import java.io.IOException;

/**
 * Основной класс FCGI сервера.
 * Внутри него, помимо приёма и отправки запросов, так же реализуется поддержка логики, которая происходит между
 * получением запроса на фронтенд и отправлением ответа на фронтенд.
 */
@Slf4j
public class Server {
    private final FcgiRequestBodyReader fcgiRequestBodyReader;
    private final JsonHttpRequestParser jsonHttpRequestParser;
    private final ValidationManager validationManager;
    private final ValidationRequestToCheckoutRequestParser vReqToChReqParser;
    private final CheckoutManager checkoutManager;
    private final DecodeRequestDecoder decodeRequestDecoder;
    private final JsonCoordinatesDecoder jsonCoordinatesDecoder;
    private final HttpResponseSender httpResponseSender;
    private final MinioHttpClient minioHttpClient;

    public Server() throws IOException {
        fcgiRequestBodyReader = new FcgiRequestBodyReader();
        jsonHttpRequestParser = new JsonHttpRequestParser();
        validationManager = new ValidationManager("backend/src/main/resources/validation.json");
        vReqToChReqParser = new ValidationRequestToCheckoutRequestParser();
        checkoutManager = new CheckoutManager("backend/src/main/resources/areas.json");
        decodeRequestDecoder = new DecodeRequestDecoder();
        jsonCoordinatesDecoder = new JsonCoordinatesDecoder();
        httpResponseSender = new HttpResponseSender();
        minioHttpClient = new MinioHttpClient();

        log.info("FCGI server started.");
    }

    /**
     * Метод для обработки запросов.
     * Сначала мы получаем текст запроса, вычленяем из него информацию в validationRequest.
     * Затем отправляем validationRequest на валидацию, если валидация прошла успешно, то преобразуем запрос на валидацию
     * в запрос на проверку условию checkoutRequest, который отправляем checkoutManager.
     * Под капотом checkoutManager создаются необходимые области из конфига и поочередно идет проверка на вхождение точки.
     * Результат проверки сохраняется в status, затем преобразуется в запрос на декодирование в json, декодируется
     * и отправляется ответ.
     */
    public void listenAndServe() {
        FCGIInterface fcgiInterface = new FCGIInterface();
        while (fcgiInterface.FCGIaccept() >= 0) {
            ParseRequestBodyRequest request = fcgiRequestBodyReader.readRequestBody();

            ValidationManagerOutRequest validationManagerOutRequest  = jsonHttpRequestParser.parse(request);

            if (validationManagerOutRequest != null) {
                if (validationManagerOutRequest instanceof ValidationRequest validationRequest) {
                    if (validationManager.validate(validationRequest)) {
                        CheckoutRequest checkoutRequest = vReqToChReqParser.parse(validationRequest);

                        boolean status = checkoutManager.checkRequest(checkoutRequest);

                        DecodeRequest decodeRequest = decodeRequestDecoder.decode(checkoutRequest, status);
                        String content = jsonCoordinatesDecoder.decode(decodeRequest);

                        httpResponseSender.sendHttpResponse(HttpResponseCode.Ok, content);
                    } else {
                        httpResponseSender.sendHttpResponse(HttpResponseCode.UnprocessableEntity, "");
                        log.info("Validation request failed.");
                    }
                } else {
                    String content = minioHttpClient.getResponse().getBody();
                    httpResponseSender.sendHttpResponse(HttpResponseCode.Ok, content);
                }

            } else {
                httpResponseSender.sendHttpResponse(HttpResponseCode.BadRequest, "");
            }

        }
    }
}
