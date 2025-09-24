package minio.client.unirest;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import minio.client.interfaces.HttpClient;
import minio.client.pojo.PointStatus;
import minio.client.pojo.Storage;
import minio.generators.implementations.GetObjectLinkGenerator;
import minio.generators.implementations.PutObjectLinkGenerator;
import minio.generators.interfaces.LinkGenerator;
import lombok.Setter;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Класс реализующий минималистического http-клиента для общения с MinIO.
 * Он умеет только получать содержимое файла с бакета и закидывать файл обратно на сервер.
 */
@Setter
@Slf4j
public class MinioHttpClient implements HttpClient {
    private final LinkGenerator getLinkGenerator = new GetObjectLinkGenerator();
    private final LinkGenerator putLinkGenerator = new PutObjectLinkGenerator();
    /**
     * Метод для реализации Http-метода GET для MinIO.
     * Сначала генерируется
     *
     * @return данные, которые требовалось получить с помощью get-запроса.
     */
    @Override
    public Response getResponse() throws IOException {
        String accessGetLink = getLinkGenerator.generateLink();

        Request request = new Request.Builder()
                .url(accessGetLink)
                .header("User-Agent", "OkHttp")
                .build();

        log.info("Successfully got data from MinIO.");

        return new OkHttpClient().newCall(request).execute();
    }
    /**
     * Метод для реализации Http-метода POST для MinIO.
     *
     * @param body тело отправляемого запроса.
     */
    @Override
    public void putResponse(String body) throws IOException {
        String contentString = getResponse().body().string();
        Storage content = new Gson().fromJson(contentString, Storage.class);
        if (content.getPointStatusList() == null) {
            content.setPointStatusList(new ArrayList<>());
        }

        PointStatus pointStatus = new Gson().fromJson(body, PointStatus.class);
        content.getPointStatusList().add(pointStatus);
        String newBody = new Gson().toJson(content);
        String accessPutLink = putLinkGenerator.generateLink();

        String url = accessPutLink.split(" -F ")[0].split(" ")[1];

        Map<String, String> form = Arrays.stream(accessPutLink.split(" -F "))
                .skip(1)
                .map(s -> s.split("=", 2))
                .filter(a -> a.length == 2)
                .collect(java.util.stream.Collectors.toMap(
                        a -> a[0].trim(),
                        a -> a[1].trim().replaceAll("^\"|\"$", "")
                ));

        byte[] jsonBytes = newBody.getBytes(StandardCharsets.UTF_8);

        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("policy",          form.get("policy"))
                .addFormDataPart("x-amz-algorithm", form.get("x-amz-algorithm"))
                .addFormDataPart("x-amz-credential",form.get("x-amz-credential"))
                .addFormDataPart("x-amz-date",      form.get("x-amz-date"))
                .addFormDataPart("x-amz-signature", form.get("x-amz-signature"))
                .addFormDataPart("bucket",          form.get("bucket"))
                .addFormDataPart("key",             form.get("key"))
                .addFormDataPart("file", "test.json",
                        RequestBody.create(jsonBytes, MediaType.parse("application/json")))
                .build();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response resp = client.newCall(request).execute()) {
            if (!resp.isSuccessful()) {
                throw new IOException("POST failed: " + resp.code() + "\n" + resp.body().string());
            }
        }

        log.info("Successfully send data to MinIO.");
    }
}
