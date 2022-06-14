package Controller;

import com.squareup.okhttp.*;

import java.io.IOException;

public class APIConnection {
    private final OkHttpClient client;
    private final MediaType mediaType;

    public APIConnection(){
        this.client = new OkHttpClient();
        this.mediaType = MediaType.parse("application/json");
    }
    private static final class InstanceHolder {
        static final APIConnection instance = new APIConnection();
    }

    public static APIConnection getInstance(){
        return InstanceHolder.instance;
    }

    public Response sendGetRequest(String rUrl) throws IOException {
        Request request = new Request.Builder().url(rUrl).get().build();
        return client.newCall(request).execute();
    }

    public Response sendPostRequest(String fUrl, String jsonBody, String apiKey) throws IOException {
        RequestBody body = RequestBody.create(mediaType, jsonBody);
        Request request = new Request.Builder()
                .url(fUrl)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", apiKey)
                .build();
        return client.newCall(request).execute();
    }
}
