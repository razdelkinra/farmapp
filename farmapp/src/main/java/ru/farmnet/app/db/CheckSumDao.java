package ru.farmnet.app.db;

import ru.farmnet.app.exception.AppException;

import java.sql.SQLException;

/**
 * DAO для соранения и получения контрольной суммы
 */
public interface CheckSumDao {

    /**
     * Получить контрольную сумму
     *
     * @return контрольную сумму
     */
    String getCheckSum() throws SQLException, AppException;

    /**
     * Обновить контрольную сумму. В базе хранится только актуальное значение версии
     */
    void updateCheckSum(String checkSum) throws SQLException, AppException;
}
