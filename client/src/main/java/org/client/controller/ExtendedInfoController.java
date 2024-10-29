package org.client.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.client.util.FXButton_Effects;
import org.client.util.FXLoader;
import org.example.backend.entity.Animal;
import org.client.entity.FXStageResult;
import org.example.backend.service.AnimalService;

import java.io.IOException;

public class ExtendedInfoController {

    @FXML
    private Text EditInfoButton;

    @FXML
    private Rectangle addDesButton;

    @FXML
    private Rectangle addVacButton;

    @FXML
    private Text adoptTime;

    @FXML
    private Text age;

    @FXML
    private Text aviary;

    @FXML
    private Text breed;

    @FXML
    private Rectangle closeButton;

    @FXML
    private Text id;

    @FXML
    private ListView<String> illList;

    @FXML
    private TextArea info;

    @FXML
    private Text name;

    @FXML
    private Text owner;

    @FXML
    private Text incomeDate;

    @FXML
    private Text phone;

    @FXML
    private Rectangle removeDesButton;

    @FXML
    private Text status;

    @FXML
    private Text type;

    @FXML
    private ListView<String> vaccList;

    private String selectedDecease;
    private AnimalService animalService;
    private Animal animal;


    public Rectangle getCloseButton() {
        return closeButton;
    }

    public Text getId() {
        return id;
    }

    public void setId(Text id) {
        this.id = id;
    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    @FXML
    void initialize() {
        FXButton_Effects.addHoverEffect(closeButton);
        FXButton_Effects.addHoverEffect(addDesButton);
        FXButton_Effects.addHoverEffect(addVacButton);
        FXButton_Effects.addHoverEffect(removeDesButton);
        FXButton_Effects.addHoverEffect(EditInfoButton);
        illList.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> selectedDecease = newValue);
    }

    public void addDecease() throws IOException {
        FXStageResult<AddDeceaseController> stageResult = FXLoader
                .loadStage(AddDeceaseController.class,
                        "7_add_decease_menu.fxml", 312, 85);
        stageResult.controller().getAddConfDesButton().setOnMouseClicked(e -> {
            String newDecease = stageResult.controller().getNewDecease().getText();
            if (!newDecease.isEmpty()) {
                stageResult.stage().close();
                try {
                    animalService.addDisease(animal.getId(), newDecease);
                    updateLists();
                } catch (Exception ex) {
                    throw new RuntimeException("Something went wrong");
                }
            } else {
                throw new RuntimeException("Please fill all fields");
            }
        });
        stageResult.controller().getCloseButton()
                .setOnMouseClicked(e -> stageResult.stage().close());
        stageResult.stage().showAndWait();
        updateLists();

    }

    public void removeDecease() throws IOException {
        if (!selectedDecease.isEmpty()) {
            animalService.removeDisease(animal.getId(), selectedDecease);
        }
        updateLists();
    }

    public void addVaccine() throws IOException {
        FXStageResult<AddVaccineController> stageResult = FXLoader
                .loadStage(AddVaccineController.class,
                        "8_add_vaccine_menu.fxml", 312, 85);
        stageResult.controller().getAddConfVacButton().setOnMouseClicked(e -> {
            String newVaccine = stageResult.controller().getNewVaccine().getText();
            if (!newVaccine.isEmpty()) {
                stageResult.stage().close();
                try {
                    animalService.addVaccine(animal.getId(), newVaccine);
                    updateLists();
                } catch (Exception ex) {
                    throw new RuntimeException("Something went wrong");
                }
            } else {
                throw new RuntimeException("Please fill all fields");
            }
        });
        stageResult.controller().getCloseButton()
                .setOnMouseClicked(e -> stageResult.stage().close());
        stageResult.stage().showAndWait();
        updateLists();
    }

    public void editDescription() throws IOException {
        FXStageResult<EditInfoController> stageResult = FXLoader
                .loadStage(EditInfoController.class,
                        "9_edit_info_menu.fxml", 412, 304);
        stageResult.controller().fillInfo(animal);
        stageResult.controller().getEditInfoConfButton().setOnMouseClicked(e -> {
            String newDescription = stageResult.controller().getInfo().getText();
            if (!newDescription.isEmpty()) {
                stageResult.stage().close();
                try {
                    animalService.setDescription(animal.getId(), newDescription);
                    updateLists();
                } catch (Exception ex) {
                    throw new RuntimeException("Something went wrong");
                }
            } else {
                throw new RuntimeException("Please fill all fields");
            }
        });
        stageResult.controller().getCloseButton()
                .setOnMouseClicked(e -> stageResult.stage().close());
        stageResult.stage().showAndWait();
        updateLists();
    }

    public void fillInfoCard(Animal animal, AnimalService animalService) {
        this.animal = animal;
        this.animalService = animalService;
        id.setText(String.valueOf(animal.getId()));
        type.setText(String.valueOf(animal.getType().getName()));
        name.setText(animal.getName());
        breed.setText(animal.getBreed());
        age.setText(String.valueOf(animal.getBirthDate()));
        status.setText(animal.isAdopted() ? "Усыновлен" : "В приюте");
        aviary.setText(animal.isAdopted() ? "Дома" : String.valueOf(animal.getAviaryNum()));
        adoptTime.setText(animal.isAdopted() ? String.valueOf(animal.getAdoptDate()) : "Ещё нет:(");
        owner.setText(animal.isAdopted() ? animal.getOwner().getFullName() : "Ещё не пришёл:(");
        phone.setText(animal.isAdopted() ? animal.getOwner().getPhone() : "Ещё нет:(");
        illList.setItems(FXCollections.observableArrayList(animal.getDesList()));
        vaccList.setItems(FXCollections.observableArrayList(animal.getVaccList()));
        info.setText(animal.getDescription());
        incomeDate.setText(String.valueOf(animal.getIncomeDate()));
    }

    public void updateLists() {
        animal = animalService.animalRepository().getById(animal.getId());
        info.setText(animal.getDescription());
        illList.setItems(FXCollections.observableArrayList(animal.getDesList()));
        vaccList.setItems(FXCollections.observableArrayList(animal.getVaccList()));
        illList.refresh();
        vaccList.refresh();
    }
}
