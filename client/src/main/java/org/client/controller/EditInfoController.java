package org.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;
import org.example.backend.entity.Animal;
import org.client.util.FXButton_Effects;
import org.jetbrains.annotations.NotNull;

public class EditInfoController {
    @FXML
    private Rectangle CloseButton;

    @FXML
    private Rectangle EditInfoConfButton;

    @FXML
    private TextArea Info;

    public Rectangle getCloseButton() {
        return CloseButton;
    }

    public Rectangle getEditInfoConfButton() {
        return EditInfoConfButton;
    }

    public TextArea getInfo() {
        return Info;
    }

    public void fillInfo(@NotNull Animal animal) {
        Info.setText(animal.getDescription());
    }

    @FXML
    void initialize() {
        FXButton_Effects.addHoverEffect(CloseButton);
        FXButton_Effects.addHoverEffect(EditInfoConfButton);
    }
}
