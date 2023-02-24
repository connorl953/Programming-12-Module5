package com.connor.module5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    public TextField usernameInput;
    public TextField passwordInput;
    public Pane loginStuff;
    public Label welcomeLabel;
    public Label invalidLoginText;
    public Label registerText;
    public Pane logoutStuff;
    DatabaseHandler handler = new DatabaseHandler();
    Stage profilePageStage;


    private void loadProfileWindow() throws IOException {
        FXMLLoader profileLoader = new FXMLLoader(Application.class.getResource("profile-page.fxml"));
        profilePageStage = new Stage(StageStyle.DECORATED);
        Scene profileScene = new Scene(profileLoader.load());
        profilePageStage.setTitle("Profile Page");
        profilePageStage.setScene(profileScene);
        profilePageStage.show();
    }
    public void login(MouseEvent mouseEvent) throws SQLException, IOException {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        if(handler.login(username,password)){
        loginStuff.setVisible(false);
        logoutStuff.setVisible(true);
        welcomeLabel.setVisible(true);
        welcomeLabel.setText("Welcome, " + username);
        loadProfileWindow();
        } else {
            invalidLoginText.setVisible(true);
        }
    }

    public void register(MouseEvent mouseEvent) throws SQLException {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        registerText.setVisible(true);
        if(handler.register(username,password)){
            registerText.setText("Registration successful!");
        } else {
            registerText.setText("Username is taken!");
        }
    }

    public void logout(MouseEvent mouseEvent) {
        loginStuff.setVisible(true);
        logoutStuff.setVisible(false);
        welcomeLabel.setVisible(false);
        profilePageStage.close();
    }
}