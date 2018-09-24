package ru.farmnet.app.service;

import ru.farmnet.app.db.CheckSumDao;
import ru.farmnet.app.db.CheckSumDaoImpl;
import ru.farmnet.app.exception.AppException;

import java.sql.SQLException;

public class LibServiceImpl implements LibService {

    private final CheckSumDao checkSumDao;

    public LibServiceImpl() {
        checkSumDao = new CheckSumDaoImpl();
    }

    @Override
    public boolean compareVersion(String checksum) throws AppException {
        try {
            return checksum.equals(checkSumDao.getCheckSum());
        } catch (SQLException e) {
            new AppException("Error read checksum");
        }
        return false;
    }


    @Override
    public void saveCheckSum(String checksum) throws AppException {
        try {
            checkSumDao.updateCheckSum(checksum);
        } catch (SQLException e) {
            new AppException("Error save checksum");
        }
    }
}
