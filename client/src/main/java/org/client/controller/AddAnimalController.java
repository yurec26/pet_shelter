package org.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

import org.client.util.FXButton_Effects;


public class AddAnimalController {
    @FXML
    private Rectangle CloseButton;

    @FXML
    private Rectangle addConfButton;

    @FXML
    private TextField animalBreedText;

    @FXML
    private TextField animalNameText;

    @FXML
    private DatePicker birthDateBox;

    @FXML
    private ChoiceBox<String> chooseAnimalType;

    @FXML
    private TextField newAviaryNum;

    public Rectangle getCloseButton() {
        return CloseButton;
    }

    public Rectangle getAddConfButton() {
        return addConfButton;
    }


    public TextField getAnimalBreedText() {
        return animalBreedText;
    }


    public TextField getAnimalNameText() {
        return animalNameText;
    }


    public DatePicker getBirthDateBox() {
        return birthDateBox;
    }


    public ChoiceBox<String> getChooseAnimalType() {
        return chooseAnimalType;
    }

    public TextField getNewAviaryNum() {
        return newAviaryNum;
    }

    @FXML
    void initialize() {
        FXButton_Effects.addHoverEffect(CloseButton);
        FXButton_Effects.addHoverEffect(addConfButton);
    }
}
