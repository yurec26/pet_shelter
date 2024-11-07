package org.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import org.client.util.FXButton_Effects;

public class AddVaccineController {

    @FXML
    private Rectangle CloseButton;

    @FXML
    private Rectangle AddConfVacButton;

    @FXML
    private TextField newVaccine;

    public Rectangle getCloseButton() {
        return CloseButton;
    }


    public Rectangle getAddConfVacButton() {
        return AddConfVacButton;
    }

    public TextField getNewVaccine() {
        return newVaccine;
    }

    @FXML
    void initialize() {
        FXButton_Effects.addHoverEffect(CloseButton);
        FXButton_Effects.addHoverEffect(AddConfVacButton);
    }
}
