package org.example.backend.util;

import org.example.backend.entity.Animal;

import java.util.List;

public class MaxIDCounterUtil {
    public static int countMaxID(List<Animal> storage) {
        return  storage.stream()
                .mapToInt(Animal::getId)
                .max()
                .orElse(0);
    }
}
