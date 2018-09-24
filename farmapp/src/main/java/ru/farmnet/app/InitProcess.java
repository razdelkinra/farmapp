package ru.farmnet.app;

import ru.farmnet.app.db.JDBCDInitDB;
import ru.farmnet.app.exception.AppException;
import ru.farmnet.app.service.LibService;
import ru.farmnet.app.service.LibServiceImpl;
import ru.farmnet.app.utils.CheckSumCalculator;
import ru.farmnet.app.utils.RemoteFileDownloader;

import java.io.File;

public class InitProcess {

    public void process() throws AppException {
        new JDBCDInitDB().init();
        LibService libService = new LibServiceImpl();
        File targetFile = RemoteFileDownloader.downloadJar();
        String checksum = CheckSumCalculator.getCheckSumFile(targetFile);
        libService.saveCheckSum(checksum);
    }
}
