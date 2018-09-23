package ru.farmnet.app.service;

import ru.farmnet.app.AppException;

public interface LibService {

    boolean compareVersion(String checksum) throws AppException;

    void saveCheckSum(String checksum) throws AppException;
}
