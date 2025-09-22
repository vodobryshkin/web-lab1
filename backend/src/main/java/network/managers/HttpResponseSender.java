package network.managers;

import http.status.HttpResponseCode;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * Класс для отправки ответа обратно на фронтенд.
 */
@Slf4j
public class HttpResponseSender {
    /**
     * Метод для формирования и отправки http-ответа.
     *
     * @param httpResponseCode код ответа.
     * @param body тело ответа.
     */
    public void sendHttpResponse(HttpResponseCode httpResponseCode, String body) {
        byte[] bodyBytes = body == null ? new byte[0] : body.getBytes(StandardCharsets.UTF_8);

        String headers =
                "Status: %d %s\r\n".formatted(httpResponseCode.getCode(), httpResponseCode.getMeaning()) +
                "Content-Type: application/json; charset=utf-8\r\n" +
                "Content-Length: %d\r\n".formatted(bodyBytes.length) +
                "\r\n";

        String httpPayload = headers + body;

        log.info("Sent a response to the client: {}", httpPayload);
        System.out.print(httpPayload);
    }

}
