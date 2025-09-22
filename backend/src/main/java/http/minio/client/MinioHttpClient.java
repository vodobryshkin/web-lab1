package http.minio.client;

import http.interfaces.HttpClient;
import http.status.HttpResponseCode;
import kong.unirest.core.GetRequest;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import lombok.Setter;

@Setter
public class MinioHttpClient implements HttpClient {
    private String accessGetLink;

    public MinioHttpClient() {
        accessGetLink = "LINK";
    }

    /**
     * Метод для реализации Http-метода GET для MinIO.
     *
     * @return данные, которые требовалось получить с помощью get-запроса.
     */
    @Override
    public HttpResponse<String> getResponse() {
        GetRequest req = Unirest.get(accessGetLink)
                .header("User-Agent", "Unirest-Java");

        return req.asString();
    }

    /**
     * Метод для реализации Http-метода POST для MinIO.
     *
     * @param body тело отправляемого запроса.
     * @return код ответа http-запроса.
     */
    @Override
    public HttpResponseCode postResponse(String body) {
        return HttpResponseCode.Ok;
    }
}
