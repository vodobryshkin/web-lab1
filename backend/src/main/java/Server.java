import com.fastcgi.FCGIInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Server {
    private static final Gson gson = new Gson();

    public static void main(String[] args) throws IOException {
        var fcgiInterface = new FCGIInterface();
        while (fcgiInterface.FCGIaccept() >= 0) {
            String request = readRequestBody();

            JsonObject obj = gson.fromJson(request, JsonObject.class);

            double x = obj.get("x").getAsDouble();
            double y = obj.get("y").getAsDouble();
            double r = obj.get("r").getAsDouble();

            //TODO переделать логику
            boolean status = (x == y && y == r);

            String content = """
                    {
                        "x": %s,
                        "y": %s,
                        "r": %s,
                        "status": %s,
                    }""".formatted(status ? "true" : "false");

            var httpResponse = """
            Content-Type: application/json
            Content-Length: %d
            %s
            """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);

            System.out.println(httpResponse);
        }
    }

    private static String readRequestBody() throws IOException {
        FCGIInterface.request.inStream.fill();
        var contentLength = FCGIInterface.request.inStream.available();
        var buffer = ByteBuffer.allocate(contentLength);
        var readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);
        var requestBodyRaw = new byte[readBytes];
        buffer.get(requestBodyRaw);
        buffer.clear();
        return new String(requestBodyRaw, StandardCharsets.UTF_8);
    }
}
