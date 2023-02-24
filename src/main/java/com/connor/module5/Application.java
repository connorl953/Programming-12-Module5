package com.connor.module5;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.sql.SQLException;

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

    public static void main(String[] args) throws SQLException {
//        DatabaseHandler handler = new DatabaseHandler();
//        System.out.println(handler.checkExists("TestUser"));
//        handler.register("TestUser2","1234");
//        System.out.println(handler.login("TestUser2", "1234"));
        launch();

    }
}