package entities.status;

import lombok.Getter;

/**
 * Класс для реализации кода ответа на запрос.
 * Хранит в себе код отправленной HTML страницы при статусе.
 */
public enum HttpResponseCode {
    /**
     * Код 400 - сервер не смог понять запрос из-за недействительного синтаксиса.
     */
    BadRequest(400, "Bad Request"),

    /**
     * Код 200 - запрос выполнен успешно.
     */
    Ok(200, "OK"),

    /**
     * Код 422 - сервер понимает тип содержимого в теле запроса и синтаксис запроса является правильным,
     * но серверу не удалось обработать инструкции содержимого.
     */
    UnprocessableEntity(422, "Unprocessable Entity");

    @Getter
    private final String meaning;
    @Getter
    private final int code;

    HttpResponseCode(int code, String meaning) {
        this.code = code;
        this.meaning = meaning;
    }
}
