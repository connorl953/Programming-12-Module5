package com.connor.module5;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(Application.class.getResource("login-page.fxml"));
        Pane loginPage = loginLoader.load();
        Scene loginScene = new Scene(loginPage);
        stage.setTitle("Login");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }
}