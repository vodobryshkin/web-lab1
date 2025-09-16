package network.managers;

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
     * @param body тело ответа.
     */
    public void sendHttpResponse(String body) {
        String httpResponse = """
                Content-Type: application/json
                Content-Length: %d
                
                %s
                """.formatted(body.getBytes(StandardCharsets.UTF_8).length, body);

        log.info("Sent a response {} to the client.", httpResponse);

        System.out.println(httpResponse);
    }
}
