package ru.farmnet.app.utils;


import lombok.extern.slf4j.Slf4j;
import ru.farmnet.app.exception.AppException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class CheckSumCalculator {

    /**
     * Метод для вычисления контрольной суммы библиотеки
     */
    public static String getCheckSumFile(File file) throws AppException {
        StringBuilder checkSum = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");// MD5
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            byte[] dataBytes = new byte[1024];
            int nread;

            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            byte[] mdbytes = md.digest();

            for (int i = 0; i < mdbytes.length; i++)
                checkSum.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new AppException("Error compute the checksum file");
        }
        return checkSum.toString();
    }
}
