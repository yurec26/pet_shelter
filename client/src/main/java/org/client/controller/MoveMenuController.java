package org.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import org.client.util.FXButton_Effects;

public class MoveMenuController {

    @FXML
    private Rectangle CloseButton;

    @FXML
    private Rectangle MoveConfButton;

    @FXML
    private TextField newAviaryNumber;

    public Rectangle getCloseButton() {
        return CloseButton;
    }

    public Rectangle getMoveConfButton() {
        return MoveConfButton;
    }

    public TextField getNewAviaryNumber() {
        return newAviaryNumber;
    }

    @FXML
    void initialize() {
        FXButton_Effects.addHoverEffect(CloseButton);
        FXButton_Effects.addHoverEffect(MoveConfButton);
    }
}