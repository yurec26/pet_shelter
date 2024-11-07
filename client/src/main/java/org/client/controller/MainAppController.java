package org.client.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.example.backend.constants.AnimalType;
import org.client.constants.FXDisplayOptions;
import org.example.backend.entity.Animal;
import org.client.entity.FXStageResult;
import org.example.backend.repository.AnimalRepository;
import org.example.backend.repository.AviaryRepository;
import org.example.backend.service.AnimalService;
import org.client.util.FXButton_Effects;
import org.client.util.FXLoader;
import org.example.backend.service.AviaryService;
import org.example.backend.util.IdGeneratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

public class MainAppController {

    public static Logger logger = LoggerFactory.getLogger(MainAppController.class);

    @FXML
    private ImageView ExitButton;
    @FXML
    private TableView<Animal> tableview;
    @FXML
    private Text Text_Adopt_Button;
    @FXML
    private Rectangle AdoptButton;
    @FXML
    private Rectangle MoveButton1;
    @FXML
    private Text Text_Move_Button1;
    @FXML
    private Rectangle InfoButton1;
    @FXML
    private Text Text_Info_Button1;
    @FXML
    private Rectangle AddButton;
    @FXML
    private Text Text_Add_Button;
    @FXML
    private Rectangle FilterButton;
    @FXML
    private Text FiltertButtonText;
    @FXML
    private Rectangle AviaryInfoButton;
    @FXML
    private TableColumn<Animal, Integer> idColumn;
    @FXML
    private TableColumn<Animal, String> typeColumn;
    @FXML
    private TableColumn<Animal, String> nameColumn;
    @FXML
    private TableColumn<Animal, String> breedColumn;
    @FXML
    private TableColumn<Animal, String> birthDateColumn;
    @FXML
    private TableColumn<Animal, String> incomeDateColumn;
    @FXML
    private TableColumn<Animal, String> isAdoptedColumn;
    @FXML
    private TableColumn<Animal, String> aviaryNumColumn;
    @FXML
    private ChoiceBox<String> displayChoice;

    private Animal selectedAnimal;
    private FXDisplayOptions displayChoiceOption;

    private AnimalService animalService;
    private AviaryService aviaryService;

    @FXML
    void initialize() throws IOException {
        displayChoiceOption = FXDisplayOptions.ALL;
        animalService = new AnimalService(new AnimalRepository());
        aviaryService = new AviaryService(new AviaryRepository());
        aviaryService.getAll();
        IdGeneratorUtil.findMaxID(animalService.getAviaryList());
        setUpTableView();
        updateTableView(displayChoiceOption);
        tableview.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue)
                        -> selectedAnimal = newValue);
        displayChoice.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue)
                        -> displayChoiceOption = FXDisplayOptions.fromDisplayName(newValue));
        setUpEffect();
    }


    public void addAnimal() throws IOException {
        FXStageResult<AddAnimalController> stageResult = FXLoader
                .loadStage(AddAnimalController.class,
                        "5_add_animal_menu.fxml", 378, 374);
        stageResult.controller().getChooseAnimalType()
                .setItems(FXCollections.observableArrayList(AnimalType.DOG.getName(),
                        AnimalType.CAT.getName(), AnimalType.RABBIT.getName()));
        stageResult.controller().getAddConfButton().setOnMouseClicked(e -> {
            String animalName = stageResult.controller().getAnimalNameText().getText();
            String animalBreed = stageResult.controller().getAnimalBreedText().getText();
            LocalDate birthDate = stageResult.controller().getBirthDateBox().getValue();
            AnimalType animalType = AnimalType.fromAnimalName(stageResult.controller().getChooseAnimalType().getValue());
            int aviaryId = Integer.parseInt(stageResult.controller().getNewAviaryNum().getText());
            if (!animalName.isEmpty() && !animalBreed.isEmpty()
                    && birthDate != null && animalType != null && aviaryId != 0) {
                stageResult.stage().close();
                try {
                    if (aviaryService.isSuitable(aviaryId, animalType)) {
                        int newID = IdGeneratorUtil.setID();
                        animalService
                                .addAnimal(newID, animalType, animalName, animalBreed, birthDate, aviaryId);
                        aviaryService.add(animalType, newID, aviaryId);
                    }
                } catch (NoSuchElementException noSuchElementException) {
                    logger.warn("Wrong input data attempt in addAnimal method");
                } catch (IOException ex) {
                    throw new RuntimeException("Something went wrong");
                }
            } else {
                throw new RuntimeException("Please fill all fields");
            }
        });
        stageResult.controller().getCloseButton()
                .setOnMouseClicked(e -> stageResult.stage().close());
        stageResult.stage().showAndWait();
        updateTableView(displayChoiceOption);
    }


    public void move() throws IOException {
        try {
            if (!selectedAnimal.isAdopted()) {
                FXStageResult<MoveMenuController> stageResult = FXLoader
                        .loadStage(MoveMenuController.class,
                                "4_move_menu.fxml", 312, 85);
                stageResult.controller().getMoveConfButton().setOnMouseClicked(e -> {
                    String newAviaryNumText = stageResult.controller()
                            .getNewAviaryNumber().getText();
                    if (!newAviaryNumText.isEmpty()) {
                        int aviaryNum = Integer.parseInt(newAviaryNumText);
                        stageResult.stage().close();
                        try {
                            if (aviaryService.isSuitable(aviaryNum, selectedAnimal.getType())) {
                                aviaryService.move(selectedAnimal, aviaryNum, false);
                                animalService.moveAnimal(selectedAnimal.getId(), aviaryNum);
                            }
                            updateTableView(displayChoiceOption);
                        } catch (NoSuchElementException noSuchElementException) {
                            logger.warn("Wrong input data attempt in moveAnimal method");
                        } catch (IOException ex) {
                            throw new RuntimeException("Something went wrong");
                        }
                    } else {
                        throw new RuntimeException("Please fill all fields");
                    }
                });
                stageResult.controller().getCloseButton()
                        .setOnMouseClicked(e -> stageResult.stage().close());
                stageResult.stage().showAndWait();
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Choose a pet");
        }
    }

    public void adopt() throws IOException {
        try {
            if (!selectedAnimal.isAdopted()) {
                FXStageResult<AdoptionConfController> stageResult = FXLoader
                        .loadStage(AdoptionConfController.class,
                                "3_adoption_menu.fxml", 382, 129);
                stageResult.controller().getAdoptConfButton().setOnMouseClicked(e -> {
                    String ownerName = stageResult.controller().getNewOwnerName().getText();
                    String ownerNumber = stageResult.controller().getNewOwnerPhone().getText();
                    if (!ownerName.isEmpty() && !ownerNumber.isEmpty()) {
                        stageResult.stage().close();
                        try {
                            aviaryService.move(selectedAnimal, 0, true);
                            animalService.giveAnimal(selectedAnimal.getId(), ownerName, ownerNumber);
                        }catch (NoSuchElementException noSuchElementException) {
                            logger.warn("Wrong input data attempt in adopt method");
                        } catch (IOException ex) {
                            throw new RuntimeException("Something went wrong");
                        }
                    } else {
                        throw new RuntimeException("Please fill all fields");
                    }
                });
                stageResult.controller().getCloseButton()
                        .setOnMouseClicked(e -> stageResult.stage().close());
                stageResult.stage().showAndWait();
                updateTableView(displayChoiceOption);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Choose a pet");
        }
    }


    public void infoCard() throws IOException {
        if (selectedAnimal != null) {
            FXStageResult<ExtendedInfoController> stageResult = FXLoader
                    .loadStage(ExtendedInfoController.class,
                            "6_extended_info.fxml", 505, 584);
            stageResult.controller().fillInfoCard(selectedAnimal, animalService);
            stageResult.controller().getCloseButton()
                    .setOnMouseClicked(e -> stageResult.stage().close());
            stageResult.stage().showAndWait();
        }
    }

    public void filter() throws IOException {
        updateTableView(displayChoiceOption);
    }

    public void aviaryInfo() throws IOException {
        FXStageResult<AviariesInfoController> stageResult = FXLoader
                .loadStage(AviariesInfoController.class,
                        "10_aviaries_info.fxml", 519, 298);
        stageResult.controller().fillTable(aviaryService);
        stageResult.controller().getCloseButton().setOnMouseClicked(e -> stageResult.stage().close());
        stageResult.stage().showAndWait();
    }

    public void setUpTableView() {
        displayChoice.setItems(FXDisplayOptions.getDisplayOptions());
        displayChoice.setValue(FXDisplayOptions.ALL.getDisplayName());
        idColumn.setCellValueFactory(cellData
                -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        typeColumn.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getType().getName()));
        nameColumn.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getName()));
        breedColumn.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getBreed()));
        birthDateColumn.setCellValueFactory(cellData -> {
            LocalDate birthDate = cellData.getValue().getBirthDate();
            int age = Period.between(birthDate, LocalDate.now()).getYears();
            return new SimpleStringProperty(String.valueOf(age));
        });
        incomeDateColumn.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue()
                .getIncomeDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        isAdoptedColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue()
                        .isAdopted() ? "Усыновлен" : "В приюте"));
        aviaryNumColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue()
                        .isAdopted() ? "Дома" : String.valueOf(cellData.getValue().getAviaryNum())));

    }

    public void updateTableView(FXDisplayOptions option) throws IOException {
        tableview.getItems().clear();
        ObservableList<Animal> animalList = FXCollections.observableArrayList(
                animalService.getAviaryList().stream()
                        .filter(s -> switch (option) {
                            case CATS -> s.getType().equals(AnimalType.CAT);
                            case DOGS -> s.getType().equals(AnimalType.DOG);
                            case RABBITS -> s.getType().equals(AnimalType.RABBIT);
                            case ADOPTED -> s.isAdopted();
                            case IN_SHELTER -> !s.isAdopted();
                            case ALL -> true;
                        }).toList());
        tableview.setItems(animalList);
    }

    public void setUpEffect() {
        FXButton_Effects.addHoverEffect(ExitButton);
        FXButton_Effects.addHoverEffect(AdoptButton);
        FXButton_Effects.addHoverEffect(MoveButton1);
        FXButton_Effects.addHoverEffect(InfoButton1);
        FXButton_Effects.addHoverEffect(AddButton);
        FXButton_Effects.addHoverEffect(FilterButton);
        FXButton_Effects.addHoverEffect(AviaryInfoButton);
        FXButton_Effects.addHoverEffect(Text_Adopt_Button);
        FXButton_Effects.addHoverEffect(Text_Move_Button1);
        FXButton_Effects.addHoverEffect(Text_Info_Button1);
        FXButton_Effects.addHoverEffect(Text_Add_Button);
        FXButton_Effects.addHoverEffect(FiltertButtonText);
    }

    @FXML
    public void exit() {
        System.exit(0);
    }
}
