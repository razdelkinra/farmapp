package ru.farmnet.app;


import ru.farmnet.app.utils.RemouteFileDownloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicLoadGraphics {

    public static final String EXTERNAL_GRAPHICS = "farmapp/src/main/resources/fxml/external.fxml";


    public File load() throws AppException {
        File extFile =  null;
        try {
            File file = RemouteFileDownloader.downloadJar();
            ClassLoader cl = new URLClassLoader(new URL[]{file.toURI().toURL()});
            InputStream is = cl.getResourceAsStream("fxml/external.fxml");
            extFile = RemouteFileDownloader.convertToFile(is, EXTERNAL_GRAPHICS);
        } catch (IOException e) {
            throw new AppException(e.getMessage());
        }
        return extFile;
    }
}
