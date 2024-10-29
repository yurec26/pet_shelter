package org.client.entity;

import javafx.stage.Stage;

public record FXStageResult<T>(Stage stage, T controller) {
}
