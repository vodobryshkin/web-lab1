package http.interfaces;

import http.status.HttpResponseCode;
import kong.unirest.core.HttpResponse;

/**
 * Интерфейс для определения функциональности конкретных http-клиентов.
 */
public interface HttpClient {
    /**
     * Метод для реализации Http-метода GET.
     *
     * @return данные, которые требовалось получить с помощью get-запроса.
     */
    HttpResponse<String> getResponse();

    /**
     * Метод для реализации Http-метода POST.
     *
     * @param body тело отправляемого запроса.
     * @return код ответа http-запроса.
     */
    HttpResponseCode postResponse(String body);
}
