package org.client.constants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;


public enum FXDisplayOptions {
    CATS("Кошки"),
    DOGS("Собаки"),
    RABBITS("Кролики"),
    ALL("Все"),
    ADOPTED("Усыновленные"),
    IN_SHELTER("В приюте");

    private final String displayName;

    FXDisplayOptions(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ObservableList<String> getDisplayOptions() {
        return FXCollections.observableArrayList(
                CATS.getDisplayName(),
                DOGS.getDisplayName(),
                RABBITS.getDisplayName(),
                ALL.getDisplayName(),
                ADOPTED.getDisplayName(),
                IN_SHELTER.getDisplayName()
        );
    }

    public static FXDisplayOptions fromDisplayName(String displayName) {
        return Arrays.stream(values())
                .filter(type -> type.getDisplayName().equals(displayName))
                .findFirst()
                .orElse(null);
    }
}
