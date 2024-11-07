package org.example.backend.repository;

import org.example.backend.entity.Animal;
import org.example.backend.util.IOUtil;

import java.io.IOException;

public class AnimalRepository extends ShelterRepository<Animal> {

    @Override
    public void loadFromRepo() throws IOException {
        IOUtil.loadFromRepoFileAnimal(super.getAll());
    }

    @Override
    public void updateRepoFile() throws IOException {
        IOUtil.updateRepoFileAnimal(super.getAll());
    }
}
