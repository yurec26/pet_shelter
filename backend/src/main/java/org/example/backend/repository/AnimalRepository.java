package org.example.backend.repository;

import org.example.backend.entity.Animal;
import org.example.backend.util.IOUtil;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AnimalRepository implements ShelterRepository<Animal> {

    private final List<Animal> animalList = new CopyOnWriteArrayList<>();

    public AnimalRepository add(Animal animal) {
        animalList.add(animal);
        return this;
    }

    @Override
    public Animal getById(int id) {
        return animalList.stream().filter(s -> s.getId() == id).findAny().orElse(null);
    }

    @Override
    public AnimalRepository update(Animal animal) {
        animalList.set(animalList.indexOf(animal), animal);
        return this;
    }

    @Override
    public List<Animal> getAll() {
        return animalList;
    }

    @Override
    public AnimalRepository loadFromRepo() throws IOException {
        IOUtil.loadFromRepoFileAnimal(animalList, Animal.class);
        return this;
    }

    @Override
    public void updateRepoFile() throws IOException {
        IOUtil.updateRepoFileAnimal(animalList);
    }
}
