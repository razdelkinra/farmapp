package ru.farmnet.app.db;

import lombok.extern.slf4j.Slf4j;
import ru.farmnet.app.exception.AppException;

import java.sql.*;

@Slf4j
public class CheckSumDaoImpl implements CheckSumDao {

    public static final String GET_CHECKSUM = "SELECT FIRST 1 check_sum FROM lib";
    public static final String UPDATE_CHECKSUM =
            "UPDATE lib set check_sum = ? where check_sum = (SELECT FIRST 1 check_sum FROM lib)";


    @Override
    public String getCheckSum() throws SQLException, AppException {
        String checkSum = "";
        try (Connection conn = DataSource.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(GET_CHECKSUM);
            while (rs.next()) {
                checkSum = rs.getString("check_sum");
            }
            rs.close();
            return checkSum;
        }
    }

    @Override
    public void updateCheckSum(String checkSum) throws SQLException, AppException {
        try (Connection conn = DataSource.getInstance().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_CHECKSUM);
            stmt.setString(1, checkSum);
            stmt.executeUpdate();
        }
    }
}
