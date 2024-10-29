package org.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.client.controller.HelloController;
import org.client.util.FXLoader;
import org.client.entity.FXStageResult;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXStageResult<HelloController> stageResult = FXLoader
                .loadStage(HelloController.class, "0_hello-view.fxml", 950, 650);
        stageResult.stage().show();
    }

    public static void main(String[] args) {
        launch();
    }
}