package ru.farmnet.app.utils;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import ru.farmnet.app.exception.AppException;

import java.io.File;
import java.io.IOException;

public class RemoteFileDownloader {

    public static final String SERVER_JAR_FILE_URL = "http://localhost:8083/simple.jar";
    public static final String LOCAL_PATH_JAR_FILE = "farmapp/src/main/resources/server.jar";

    /**
     * Загружает библиотеку с сервера
     */
    public static File downloadJar() throws AppException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpResponse response;
        try {
            response = client.execute(new HttpGet(SERVER_JAR_FILE_URL));
            return FileConverter.convertToFile(response.getEntity().getContent(), LOCAL_PATH_JAR_FILE);
        } catch (IOException e) {
            throw new AppException("Server not available");
        }
    }
}
