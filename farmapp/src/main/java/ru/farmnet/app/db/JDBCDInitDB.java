package ru.farmnet.app.db;

import lombok.extern.slf4j.Slf4j;
import ru.farmnet.app.exception.AppException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class JDBCDInitDB implements InitDB {

    public static final String SQL_CREATE_TABLE =
            " EXECUTE BLOCK AS BEGIN \n"
                    + " if ((select 1 from rdb$relations where rdb$relation_name = 'lib_version') is not null) then "
                    + " execute statement "
                    + " 'CREATE TABLE lib_version"
                    + " (id           NOT NULL AUTO_INCREMENT,"
                    + " check_sun    VARCHAR(100),"
                    + " number       TIMESTAMP DEFAULT CURRENT_TIMESTAMP);'; " +
                    " END";

    /**
     * Создает таблицу в БД, если она не создана
     */
    @Override
    public void init() throws AppException {
        try (Connection conn = DataSource.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(SQL_CREATE_TABLE);
        } catch (SQLException se) {
            throw new AppException("Error create init table");
        }
        log.info("Initial loading the table finished");
    }
}
