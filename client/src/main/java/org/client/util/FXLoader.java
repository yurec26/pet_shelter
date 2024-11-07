package org.client.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.client.entity.FXStageResult;

import java.io.IOException;

public class FXLoader {

    public static <T> FXStageResult<T> loadStage(Class<T> clazz, String fxml, int width, int height) throws IOException {
        FXMLLoader loader = new FXMLLoader(clazz.getResource(fxml));
        Parent root = loader.load();
        T controller = loader.getController();
        Stage inputStage = new Stage();
        inputStage.initStyle(StageStyle.UNDECORATED);
        inputStage.setResizable(false);
        inputStage.setScene(new Scene(root, width, height));
        return new FXStageResult<>(inputStage, controller);
    }
}
