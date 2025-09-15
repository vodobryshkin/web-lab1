package network.managers;

import java.nio.charset.StandardCharsets;

/**
 * Класс для отправки ответа обратно на фронтенд.
 */
public class HttpResponseSender {
    /**
     * Метод для формирования http-ответа.
     *
     * @param body тело ответа.
     */
    public void sendHttpResponse(String body) {
        String httpResponse = """
                Content-Type: application/json
                Content-Length: %d
                
                %s
                """.formatted(body.getBytes(StandardCharsets.UTF_8).length, body);
        System.out.println(httpResponse);
    }
}
