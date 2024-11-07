package org.example.backend.repository;

import org.example.backend.entity.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class ShelterRepository<T extends Entity> {
    List<T> entities = new ArrayList<>();

    public Optional<T> getById(int id) {
        return entities.stream().filter(s -> s.getId() == id).findAny();
    }

    public void update(T entity) {
        entities.set(entities.indexOf(entity), entity);
    }

    public List<T> getAll() {
        return entities;
    }

    public void add(T entity){
        entities.add(entity);
    }

    abstract void loadFromRepo() throws IOException;

    abstract void updateRepoFile() throws IOException;
}

