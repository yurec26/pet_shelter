package org.example.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.backend.entity.Animal;
import org.example.backend.entity.Aviary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class IOUtil {
    private static final ObjectMapper objectMapper
            = new ObjectMapper().registerModule(new JavaTimeModule());

    private static final String AVIARIES_REPO_FILE = "backend/src/main/resources/aviaries.json";

    private static final String ANIMALS_REPO_FILE = "backend/src/main/resources/animals.json";

    public static void loadFromRepoFileAnimal(List<Animal> list) throws IOException {
        list.clear();
        list.addAll(jsonToList(ANIMALS_REPO_FILE, Animal.class));
    }

    public static void loadFromRepoFileAviary(List<Aviary> list) throws IOException {
        list.clear();
        list.addAll(jsonToList(AVIARIES_REPO_FILE, Aviary.class));
    }

    public static void updateRepoFileAviary(List<Aviary> list) throws IOException {
        listToJSON(list, AVIARIES_REPO_FILE);
    }

    public static void updateRepoFileAnimal(List<Animal> list) throws IOException {
        listToJSON(list, ANIMALS_REPO_FILE);
    }


    public static <T> List<T> jsonToList(String path, Class<T> clazz) throws IOException {
        return objectMapper.readValue(Files.readString(Path.of(path)),
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static <V> void listToJSON(List<V> list, String url) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(url), list);
    }

}
