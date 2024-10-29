package org.example.backend.util;

import org.example.backend.entity.Animal;

import java.util.List;

public class IdGeneratorUtil {
    private static int idCounter;

    public static int setID() {
        idCounter++;
        return idCounter;
    }

    public static void findMaxID(List<Animal> animals) {
        idCounter = MaxIDCounterUtil.countMaxID(animals);
    }
}
