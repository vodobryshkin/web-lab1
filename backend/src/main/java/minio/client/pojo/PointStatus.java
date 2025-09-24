package minio.client.pojo;

import lombok.Data;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс для реализации удобного парсинга в объект с get-запроса.
 */
@Data
public class PointStatus {
    private String x;
    private String y;
    private String r;
    private Boolean status;
    private final String date = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
}
