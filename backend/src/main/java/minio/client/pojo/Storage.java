package minio.client.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для реализации удобного парсинга в объект с get-запроса.
 */
@Data
public class Storage {
    @SerializedName("points")
    private List<PointStatus> pointStatusList;

    public Storage() {
        pointStatusList = new ArrayList<>();
    }
}
