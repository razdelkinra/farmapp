package ru.farmnet.app.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import ru.farmnet.app.exception.AppException;
import ru.farmnet.app.utils.DynamicGraphicsLoader;
import ru.farmnet.app.service.LibService;
import ru.farmnet.app.service.LibServiceImpl;
import ru.farmnet.app.utils.CheckSumCalculator;

import java.io.File;
import java.io.IOException;

@Slf4j
public class Controller {

    @FXML
    private Button openbutton;
    @FXML
    private Button chekbutton;

    @FXML
    private TableView table;

    @FXML
    public void buttonAction(ActionEvent event) {
        LibService libService = new LibServiceImpl();
        String checksum = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Jar file");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Jar files (*.jar)", "*.jar");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                checksum = CheckSumCalculator.getCheckSumFile(file);
                if (libService.compareVersion(checksum)) {
                    //TODO: создай загружаемый файл с сервера так что бы у них checksum были разные. сейчас они совпадают почему то. В if потом поставь '!'
                    addTab(DynamicGraphicsLoader.load());
                }
            } catch (AppException e) {
                log.error(e.getMessage()); //TODO: выброси окно c ошибкой
            }
        }
    }

    private void addTab(File file) throws AppException {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(file.toURL());
            AnchorPane anchorPane = loader.load();
            ObservableList list = ((TableView) anchorPane.getChildren().get(0).lookup("TableView")).getItems();
            table.getItems().addAll(list);
        } catch (IOException e) {
            throw new AppException("Failed to load data");
        }
    }
}
