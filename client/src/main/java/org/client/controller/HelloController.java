package org.client.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.client.util.FXButton_Effects;

public class HelloController {
    @FXML
    private ImageView ExitButton;

    @FXML
    private Rectangle LogInButton;

    @FXML
    void initialize() {
        FXButton_Effects.addHoverEffect(ExitButton);
        FXButton_Effects.addHoverEffect(LogInButton);
    }

    @FXML
    void handleButtonAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("1_main_window.fxml"));
        Scene newScene = new Scene(loader.load(), 950, 650);
        Stage currentStage = (Stage) LogInButton.getScene().getWindow();
        currentStage.setScene(newScene);
        currentStage.show();
    }

    @FXML
    public void exit() {
        System.exit(0);
    }
}
