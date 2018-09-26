package ru.farmnet.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import ru.farmnet.app.exception.AppException;
import ru.farmnet.app.fxcomponent.Dialog;
import ru.farmnet.app.service.LibService;
import ru.farmnet.app.service.LibServiceImpl;
import ru.farmnet.app.utils.CheckSumCalculator;
import ru.farmnet.app.utils.GraphicsLoader;

import java.io.File;
import java.io.IOException;

@Slf4j
public class Controller {

    File file;

    @FXML
    AnchorPane newWindow;

    @FXML
    public void load(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Jar file");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Jar files (*.jar)", "*.jar");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(new Stage());
        Dialog.showAlertWithHeaderText("Compare version");
    }

    @FXML
    public void compare(ActionEvent event) {
        LibService libService = new LibServiceImpl();
        if (file != null) {
            try {
                if (!libService.compareVersion(CheckSumCalculator.getCheckSumFile(file))) {
                    addTab(GraphicsLoader.loadFromServer());
                    Dialog.showAlertWithHeaderText("Updates found. The new version is downloaded.");
                } else {
                    addTab(GraphicsLoader.loadFromResource());
                    Dialog.showAlertWithHeaderText("No updates found");
                }
            } catch (AppException e) {
                Dialog.showErrorDialog(e);
            }
        }
    }

    private void addTab(File file) throws AppException {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(file.toURI().toURL());
            AnchorPane anchorPane = loader.load();
            newWindow.getChildren().setAll(anchorPane);
        } catch (IOException e) {
            throw new AppException("Failed to load external graphics");
        }
    }
}
