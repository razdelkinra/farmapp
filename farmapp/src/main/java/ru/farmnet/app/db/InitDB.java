package ru.farmnet.app.db;

import ru.farmnet.app.exception.AppException;

public interface InitDB {

    /**
     * Действия выполняемые при старте приложения.
     * Запросить актуальную версию с сервера и записать в БД
     */
    void init() throws AppException;
}
