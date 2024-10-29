package org.example.backend.repository;

import java.io.IOException;
import java.util.List;

public interface ShelterRepository<T> {

     T getById(int id);

     ShelterRepository<T> update(T entity);

     List<T> getAll();

    ShelterRepository<T> loadFromRepo() throws IOException;

    void updateRepoFile() throws IOException;
}
