package ru.farmnet.app.utils;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import ru.farmnet.app.AppException;
import ru.farmnet.app.db.InitDB;

import java.io.*;

public class RemouteFileDownloader {

    public static final String SERVER_JAR_FILE_URL = "http://localhost:8083/simple.jar";
    public static final String LOCAL_PATH_JAR_FILE = "farmapp/src/main/resources/server.jar";

    public static File downloadJar() throws AppException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpResponse response;
        try {
            response = client.execute(new HttpGet(SERVER_JAR_FILE_URL));
            return convertToFile(response.getEntity().getContent(), LOCAL_PATH_JAR_FILE);
        } catch (IOException e) {
            throw new AppException("Server not available");
        }
    }

    public static File convertToFile(InputStream is, String filePath) throws IOException {
        File targetFile = new File(filePath);
        OutputStream outStream = new FileOutputStream(targetFile);
        byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        return targetFile;
    }

}
