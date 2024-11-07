package org.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import org.client.util.FXButton_Effects;

public class AddDeceaseController {

    @FXML
    private Rectangle CloseButton;

    @FXML
    private Rectangle AddConfDesButton;

    @FXML
    private TextField newDecease;

    public Rectangle getCloseButton() {
        return CloseButton;
    }

    public Rectangle getAddConfDesButton() {
        return AddConfDesButton;
    }

    public TextField getNewDecease() {
        return newDecease;
    }

    @FXML
    void initialize() {
        FXButton_Effects.addHoverEffect(CloseButton);
        FXButton_Effects.addHoverEffect(AddConfDesButton);
    }
}
