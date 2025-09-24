package minio.client.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Класс для реализации удобного парсинга в объект с get-запроса.
 */
@Data
public class PointStatus {
    private String x;
    private String y;
    private String r;
    private Boolean status;
    @SerializedName("current_time")
    private String date;
    private long duration;
}
