package minio.generators.interfaces;

import java.io.IOException;

/**
 * Интерфейс для определения функциональности создания presigned url в MinIO.
 */
public interface LinkGenerator {
    /**
     * Метод для генерации ссылки на какое-либо действие
     *
     * @return сгенерированная на определённое действие presigned ссылка.
     */
    String generateLink() throws IOException;
}
