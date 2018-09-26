package ru.farmnet.app.utils;


import ru.farmnet.app.exception.AppException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

public class GraphicsLoader {

    public static final String EXTERNAL_FXML = "fxml/external.fxml";
    public static final String RESOURCE_PATH = "farmapp/src/main/resources/";

    /**
     * Метод для динамической загузки fxml файла из jar, который лежит на сервере
     */
    public static File loadFromServer() throws AppException {
        try {
            File file = RemoteFileDownloader.downloadJar();
            ClassLoader cl = new URLClassLoader(new URL[]{file.toURI().toURL()});
            InputStream is = cl.getResourceAsStream(EXTERNAL_FXML);
            return FileConverter.convertToFile(is, EXTERNAL_FXML + RESOURCE_PATH);
        } catch (IOException e) {
            throw new AppException("Error loading graphics from the uploaded file");
        }
    }

    /**
     * Метод для динамической загузки fxml файла из jar, который есть у нас на жестком диске
     */
    public static File loadFromResource() throws AppException {
        try {
            InputStream is = GraphicsLoader.class.getResourceAsStream(EXTERNAL_FXML);
            return FileConverter.convertToFile(is, EXTERNAL_FXML + RESOURCE_PATH);
        } catch (IOException e) {
            throw new AppException("Error loading graphics from the uploaded file");
        }
    }
}
