package ru.farmnet.app.utils;

import java.io.*;

public class FileConverter {

    /**
     * Метод для конвертации потока байтов в файл
     */
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
