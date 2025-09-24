package network.managers;

import com.fastcgi.FCGIInterface;
import entities.request.implementations.network.ParseRequestBodyRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Класс для чтения тела поступившего запроса.
 */
@Slf4j
public class FcgiRequestBodyReader {
    /**
     * Метод для чтения поступившего запроса и создания с его помощью запроса на парсинг.
     *
     * @return созданный запрос на парсинг тела поступившего http-запроса.
     */
    public ParseRequestBodyRequest readRequestBody() {
        try {
            FCGIInterface.request.inStream.fill();

            int contentLength = FCGIInterface.request.inStream.available();
            ByteBuffer buffer = ByteBuffer.allocate(contentLength);

            int readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);
            byte[] requestBodyRaw = new byte[readBytes];

            buffer.get(requestBodyRaw);
            buffer.clear();

            String requestBody = new String(requestBodyRaw, StandardCharsets.UTF_8);

            Properties props = FCGIInterface.request.params;

            Map<String, String> propsMap = props.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            e -> String.valueOf(e.getKey()),
                            e -> String.valueOf(e.getValue())
                    ));

            String method = propsMap.get("REQUEST_METHOD");

            if (method.equals("GET")) {
                requestBody = propsMap.get("QUERY_STRING");
            }

            log.info("Successfully read request body: {}", requestBody);

            return new ParseRequestBodyRequest(requestBody, method);
        } catch (IOException e) {
            log.error("Error while reading request body.");
            return null;
        }
    }
}
