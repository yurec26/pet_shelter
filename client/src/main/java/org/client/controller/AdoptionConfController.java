package org.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import org.client.util.FXButton_Effects;


public class AdoptionConfController {
    @FXML
    private TextField newOwnerName;

    @FXML
    private TextField newOwnerPhone;
    @FXML
    private Rectangle AdoptConfButton;

    @FXML
    private Rectangle CloseButton;

    public TextField getNewOwnerName() {
        return newOwnerName;
    }

    public TextField getNewOwnerPhone() {
        return newOwnerPhone;
    }

    public Rectangle getAdoptConfButton() {
        return AdoptConfButton;
    }

    public Rectangle getCloseButton() {
        return CloseButton;
    }

    @FXML
    void initialize() {
        FXButton_Effects.addHoverEffect(CloseButton);
        FXButton_Effects.addHoverEffect(AdoptConfButton);
    }
}
