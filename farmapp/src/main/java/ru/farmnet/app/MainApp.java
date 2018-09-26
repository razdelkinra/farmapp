package ru.farmnet.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.farmnet.app.exception.AppException;
import ru.farmnet.app.fxcomponent.Dialog;


public class MainApp extends Application {

    private static String INIT_MESSAGE;

    public static void main(String[] args) {
        launch(args);
    }

    private static void loadServerLib() {
        InitProcess initProcess = new InitProcess();
        try {
            initProcess.process();
        } catch (AppException e) {
            INIT_MESSAGE = e.getMessage();
        }
        if (INIT_MESSAGE != null) {
            Dialog.showAlertWithHeaderText(INIT_MESSAGE);
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            String fxmlFile = "/fxml/sample.fxml";
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            stage.setTitle("JavaFX and Maven");
            stage.setScene(new Scene(root));
            stage.show();
            loadServerLib();
        } catch (Exception e) {
            Dialog.showErrorDialog(e);
        }
    }
}