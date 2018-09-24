package ru.farmnet.app.utils;


import ru.farmnet.app.exception.AppException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicGraphicsLoader {

    public static final String EXTERNAL_GRAPHICS = "farmapp/src/main/resources/fxml/external.fxml";

    /**
     * Метод для динамической загузки из jar файла с графикой
     */
    public static File load() throws AppException {
        try {
            File file = RemoteFileDownloader.downloadJar();
            ClassLoader cl = new URLClassLoader(new URL[]{file.toURI().toURL()});
            InputStream is = cl.getResourceAsStream("fxml/external.fxml");
            return FileConverter.convertToFile(is, EXTERNAL_GRAPHICS);
        } catch (IOException e) {
            throw new AppException("Error loading graphics from the uploaded file");
        }
    }
}
