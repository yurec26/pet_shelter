package org.client.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.shape.Rectangle;
import org.example.backend.constants.AviaryConstants;
import org.example.backend.entity.Aviary;
import org.client.util.FXButton_Effects;
import org.example.backend.service.AviaryService;

import java.io.IOException;
import java.util.List;

public class AviariesInfoController {
    @FXML
    private TableView<Aviary> AviaryView;

    @FXML
    private Rectangle CloseButton;

    @FXML
    private TableColumn<Aviary, Double> capacity;

    @FXML
    private TableColumn<Aviary, Integer> id;

    @FXML
    private TableColumn<Aviary, Integer> inStock;

    @FXML
    private TableColumn<Aviary, Double> spaceAval;

    @FXML
    private TableColumn<Aviary, String> type;

    public Rectangle getCloseButton() {
        return CloseButton;
    }

    public TableColumn<Aviary, Integer> getId() {
        return id;
    }

    public void setId(TableColumn<Aviary, Integer> id) {
        this.id = id;
    }

    @FXML
    void initialize() {
        FXButton_Effects.addHoverEffect(CloseButton);
    }

    public void fillTable(AviaryService aviaryService) throws IOException {

        id.setCellValueFactory(cellData
                -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        capacity.setCellValueFactory(cellData
                -> new SimpleDoubleProperty(cellData.getValue().getCapacity()).asObject());
        inStock.setCellValueFactory(cellData
                -> new SimpleIntegerProperty(cellData.getValue().getAnimalList().size()).asObject());
        spaceAval.setCellValueFactory(cellData
                -> new SimpleDoubleProperty(cellData.getValue().getSpaceAvailable()).asObject());
        type.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getType()
                .getName()));
        List<Aviary> aviaries = aviaryService.getAll().stream()
                .filter(aviary -> aviary.getId() != AviaryConstants.ADOPT_AVIARY_NUM)
                .toList();
        AviaryView.getItems().setAll(aviaries);
    }
}




