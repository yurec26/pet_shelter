package org.example.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class JsonUtil {

    private static final ObjectMapper objectMapper
            = new ObjectMapper().registerModule(new JavaTimeModule());

    public static <T> List<T> jsonToMap(String path, Class<T> clazz) throws IOException {
        return objectMapper.readValue(Files.readString(Path.of(path)),
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static <V> void listToJSON(List<V> list, String url) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(url), list);
    }

}
