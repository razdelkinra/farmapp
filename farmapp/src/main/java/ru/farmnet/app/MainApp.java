package ru.farmnet.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.farmnet.app.db.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainApp extends Application {


    private static String INIT_MESSAGE;

    public static void main(String[] args) {
        initApp();
        launch(args);
    }

    private static void initApp() {
        InitProcess initProcess = new InitProcess();
        try {
            initProcess.process();
        } catch (AppException e) {
            INIT_MESSAGE = e.getMessage();
        }
        INIT_MESSAGE = "OK"; // отобразить в окне приложения
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/sample.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setTitle("JavaFX and Maven");

        stage.setScene(new Scene(root));
        stage.show();
    }
}