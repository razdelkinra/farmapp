package ru.farmnet.app.db;

import ru.farmnet.app.AppException;

import java.sql.SQLException;

public interface CheckSumDao {

    String getCheckSum() throws AppException, SQLException;

    void updateCheckSum(String checkSum) throws AppException, SQLException;
}
