package minio.client.interfaces;

import okhttp3.Response;

import java.io.IOException;

/**
 * Интерфейс для определения функциональности конкретных http-клиентов.
 */
public interface HttpClient {
    /**
     * Метод для реализации Http-метода GET.
     *
     * @return данные, которые требовалось получить с помощью get-запроса.
     */
    Response getResponse() throws IOException;

    /**
     * Метод для реализации Http-метода POST.
     *
     * @param body тело отправляемого запроса.
     */
    void putResponse(String body) throws IOException;
}
