package com.miss.test.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * @project: miss-core
 * @package: com.miss.test.utils
 * @author: miss
 * @since: 2021/3/8 16:42
 * @history: 1.2021/3/8 created by miss
 */
public class HttpClientUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T post(String url, Object requestBody, Class<T> resType) throws IOException {

        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json;charset=UTF-8")
                .url(url)
                .post(okhttp3.RequestBody.create(OBJECT_MAPPER.writeValueAsString(requestBody), mediaType))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS).callTimeout(60, TimeUnit.SECONDS).readTimeout(50, TimeUnit.SECONDS).build();
        Response response = okHttpClient.newCall((request)).execute();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream(),"UTF-8"));
        String resString;
        StringBuilder stringBuilder = new StringBuilder();
        while ((resString = reader.readLine()) != null) {
            stringBuilder.append(resString);
        }
        return OBJECT_MAPPER.readValue(stringBuilder.toString(), resType);
    }
}
