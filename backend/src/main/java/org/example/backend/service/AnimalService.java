package org.example.backend.service;

import org.example.backend.constants.AnimalType;
import org.example.backend.entity.Animal;
import org.example.backend.entity.Owner;
import org.example.backend.repository.AnimalRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public record AnimalService(AnimalRepository animalRepository) {

    public void addAnimal(int id, AnimalType type, String name,
                          String breed, LocalDate birthDate, int aviaryNum) throws IOException {
        Animal animal = new Animal(type, name, breed, birthDate, aviaryNum);
        animal.setId(id);
        animal.setIncomeDate(LocalDate.now());
        animalRepository.add(animal);
        animalRepository.updateRepoFile();
    }

    public List<Animal> getAviaryList() throws IOException {
        animalRepository.loadFromRepo();
        return animalRepository.getAll();
    }

    public void moveAnimal(int id, int newAviary) throws IOException {
        Animal animal = animalRepository.getById(id).orElseThrow(()
                -> new NoSuchElementException("Animal with id %s not found".formatted(id)));
        animal.setAviaryNum(newAviary);
        animalRepository.update(animal);
        animalRepository.updateRepoFile();

    }

    public void addDisease(int id, String disease) throws IOException {
        Animal animal = animalRepository.getById(id).orElseThrow(()
                -> new NoSuchElementException("Animal with id %s not found".formatted(id)));
            animal.getDesList().add(disease);
            animalRepository.update(animal);
            animalRepository.updateRepoFile();

    }

    public void removeDisease(int id, String disease) throws IOException {
        Animal animal = animalRepository.getById(id).orElseThrow(()
                -> new NoSuchElementException("Animal with id %s not found".formatted(id)));
            animal.getDesList().remove(disease);
            animalRepository.update(animal);
            animalRepository.updateRepoFile();
    }

    public void addVaccine(int id, String vaccine) throws IOException {
        Animal animal = animalRepository.getById(id).orElseThrow(()
                -> new NoSuchElementException("Animal with id %s not found".formatted(id)));
            animal.getVaccList().add(vaccine);
            animalRepository.update(animal);
            animalRepository.updateRepoFile();

    }

    public void setDescription(int id, String description) throws IOException {
        Animal animal = animalRepository.getById(id).orElseThrow(()
                -> new NoSuchElementException("Animal with id %s not found".formatted(id)));
            animal.setDescription(description);
            animalRepository.update(animal);
            animalRepository.updateRepoFile();
    }

    public void giveAnimal(int id, String ownerName, String ownerPhone) throws IOException {
        Animal animal = animalRepository.getById(id).orElseThrow(()
                -> new NoSuchElementException("Animal with id %s not found".formatted(id)));
            animal.setOwner(new Owner(ownerName, ownerPhone));
            animal.setAdopted(true);
            animal.setAdoptDate(LocalDate.now());
            animal.setAviaryNum(0);
            animalRepository.update(animal);
            animalRepository.updateRepoFile();
    }
}
