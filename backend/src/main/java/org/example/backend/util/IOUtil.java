package org.example.backend.util;

import java.io.IOException;
import java.util.List;

public class IOUtil {

    private static final String AVIARIES_REPO_FILE = "backend/src/main/resources/aviaries.json";

    private static final String ANIMALS_REPO_FILE = "backend/src/main/resources/animals.json";

    public static <T> void loadFromRepoFileAnimal(List<T> list, Class<T> clazz) throws IOException {
        list.clear();
        list.addAll(JsonUtil.jsonToMap(ANIMALS_REPO_FILE, clazz));
    }

    public static <T> void loadFromRepoFileAviary(List<T> list, Class<T> clazz) throws IOException {
        list.clear();
        list.addAll(JsonUtil.jsonToMap(AVIARIES_REPO_FILE, clazz));
    }

    public static <T> void updateRepoFileAviary(List<T> list) throws IOException {
        JsonUtil.listToJSON(list, AVIARIES_REPO_FILE);
    }

    public static <T> void updateRepoFileAnimal(List<T> list) throws IOException {
        JsonUtil.listToJSON(list, ANIMALS_REPO_FILE);
    }

}
