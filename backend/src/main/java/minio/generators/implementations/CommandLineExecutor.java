package minio.generators.implementations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Класс для исполнения сформированной MinIO Client команды
 */
class CommandLineExecutor {
    public String execute(List<String> cmd) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(cmd);

        pb.redirectErrorStream(true);

        Process proc = pb.start();

        String output = "";

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(proc.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output = line;
            }
        }

        return output;
    }
}
