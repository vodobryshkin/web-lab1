package network.managers;

import com.fastcgi.FCGIInterface;
import entities.request.implementations.network.ParseRequestBodyRequest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Класс для чтения тела поступившего запроса.
 */
public class FcgiRequestBodyReader {
    /**
     * Метод для чтения поступившего запроса и создания с его помощью запроса на парсинг.
     *
     * @return созданный запрос на парсинг тела поступившего http-запроса.
     */
    public ParseRequestBodyRequest readRequestBody() throws IOException {
        FCGIInterface.request.inStream.fill();

        var contentLength = FCGIInterface.request.inStream.available();
        var buffer = ByteBuffer.allocate(contentLength);

        var readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);
        var requestBodyRaw = new byte[readBytes];

        buffer.get(requestBodyRaw);
        buffer.clear();

        return new ParseRequestBodyRequest(new String(requestBodyRaw, StandardCharsets.UTF_8));
    }
}
