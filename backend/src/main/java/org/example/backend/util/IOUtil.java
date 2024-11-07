package org.example.backend.util;

import org.example.backend.entity.Animal;
import org.example.backend.entity.Aviary;

import java.io.IOException;
import java.util.List;

public class IOUtil {

    private static final String AVIARIES_REPO_FILE = "backend/src/main/resources/aviaries.json";

    private static final String ANIMALS_REPO_FILE = "backend/src/main/resources/animals.json";

    public static void loadFromRepoFileAnimal(List<Animal> list) throws IOException {
        list.clear();
        list.addAll(JsonUtil.jsonToList(ANIMALS_REPO_FILE, Animal.class));
    }

    public static void loadFromRepoFileAviary(List<Aviary> list) throws IOException {
        list.clear();
        list.addAll(JsonUtil.jsonToList(AVIARIES_REPO_FILE, Aviary.class));
    }

    public static void updateRepoFileAviary(List<Aviary> list) throws IOException {
        JsonUtil.listToJSON(list, AVIARIES_REPO_FILE);
    }

    public static void updateRepoFileAnimal(List<Animal> list) throws IOException {
        JsonUtil.listToJSON(list, ANIMALS_REPO_FILE);
    }

}
