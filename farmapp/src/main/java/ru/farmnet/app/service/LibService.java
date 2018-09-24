package ru.farmnet.app.service;

import ru.farmnet.app.exception.AppException;

public interface LibService {

    /**
     * Сравнивает актуальную версию с версией загружаемой библиотеки
     */
    boolean compareVersion(String checksum) throws AppException;

    /**
     * Сохраняет контрольную сумму
     */
    void saveCheckSum(String checksum) throws AppException;
}
