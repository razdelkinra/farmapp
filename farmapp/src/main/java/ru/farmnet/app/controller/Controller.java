package ru.farmnet.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import ru.farmnet.app.exception.AppException;
import ru.farmnet.app.fxcomponent.Dialog;
import ru.farmnet.app.service.LibService;
import ru.farmnet.app.service.LibServiceImpl;
import ru.farmnet.app.utils.CheckSumCalculator;
import ru.farmnet.app.utils.DynamicGraphicsLoader;

import java.io.File;
import java.io.IOException;

@Slf4j
public class Controller {
    File file;
    @FXML
    AnchorPane newWindow;
    @FXML
    private Button openbutton;
    @FXML
    private Button chekbutton;
    @FXML
    private Button testbutton;

    @FXML
    public void buttonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Jar file");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Jar files (*.jar)", "*.jar");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(new Stage());
        Dialog.showAlertWithHeaderText("Сравните версии файлов");
    }

    @FXML
    public void button2Action(ActionEvent event) throws IOException {
        LibService libService = new LibServiceImpl();
        String checksum = "";
        if (file != null) {
            try {
                checksum = CheckSumCalculator.getCheckSumFile(file);
                if (!libService.compareVersion(checksum)) {
                    addTab(DynamicGraphicsLoader.load());
                    Dialog.showAlertWithHeaderText("Вы пользуетесь старой версией файла. Загружена актуальная версия.");
                } else {
                    addTab(DynamicGraphicsLoader.loadFromHdd(file));
                    Dialog.showAlertWithHeaderText("Вы пользуетесь актуальной версией файла.");
                }
            } catch (AppException e) {
                Dialog.showErrorDialog(e);
            }
        }
    }

    private void addTab(File file) throws AppException {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(file.toURL());
            AnchorPane anchorPane = loader.load();
            newWindow.getChildren().setAll(anchorPane);
        } catch (IOException e) {
            throw new AppException("Failed to load data");
        }
    }
}
