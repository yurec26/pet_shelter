package org.example.backend.constants;


import java.util.Arrays;

public enum AnimalType {
    DOG(0.1, "Собака"),
    CAT(0.02, "Кошка"),
    RABBIT(0.012, "Кролик"),
    ALL(0.0, "Любой питомец");

    private final double size;
    private final String name;


    AnimalType(double size, String name) {
        this.size = size;
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public String getName() {
        return name;
    }


    public static AnimalType fromAnimalName(String displayName) {
        return Arrays.stream(values())
                .filter(type -> type.getName().equals(displayName))
                .findFirst()
                .orElse(null);
    }
}