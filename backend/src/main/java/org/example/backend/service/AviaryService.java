package org.example.backend.service;

import org.example.backend.constants.AnimalType;
import org.example.backend.entity.Animal;
import org.example.backend.entity.Aviary;
import org.example.backend.repository.AviaryRepository;

import java.io.IOException;
import java.util.List;

public class AviaryService {
    private final AviaryRepository aviaryRepository;


    public AviaryService(AviaryRepository aviaryRepository) {
        this.aviaryRepository = aviaryRepository;
    }

    public List<Aviary> getAll() throws IOException {
        aviaryRepository.loadFromRepo();
        return aviaryRepository.getAll();
    }

    public void move(Animal animal, int newAviaryNum, boolean adopt) throws IOException {
        Aviary oldAviary = aviaryRepository.getById(animal.getAviaryNum());
        oldAviary.setSpaceAvailable(oldAviary.getSpaceAvailable() + animal.getType().getSize());
        oldAviary.getAnimalList().remove(Integer.valueOf(animal.getId()));
        if (oldAviary.getAnimalList().isEmpty()) {
            oldAviary.setType(AnimalType.ALL);
        }
        if (!adopt) {
            Aviary newAviary = aviaryRepository.getById(newAviaryNum);
            if (newAviary != null) {
                newAviary.setSpaceAvailable(newAviary.getSpaceAvailable() - animal.getType().getSize());
                if (newAviary.getAnimalList().isEmpty()) {
                    newAviary.setType(animal.getType());
                }
                newAviary.getAnimalList().add(animal.getId());

                aviaryRepository.update(newAviary);
                aviaryRepository.update(oldAviary);
                aviaryRepository.updateRepoFile();

            } else {
                throw new NullPointerException("Wrong aviary number");
            }
        } else {
            aviaryRepository.update(oldAviary);
            aviaryRepository.updateRepoFile();
        }

    }

    public void add(AnimalType type, int animalId, int aviaryId) throws IOException {
        Aviary aviary = aviaryRepository.getById(aviaryId);
        if (aviary != null) {
            if (aviary.getType() == AnimalType.ALL && aviary.getAnimalList().isEmpty()) {
                aviary.setType(type);
            }
            aviary.getAnimalList().add(animalId);
            aviary.setSpaceAvailable(aviary.getSpaceAvailable() - type.getSize());
            aviaryRepository.update(aviary);
            aviaryRepository.updateRepoFile();
        } else {
            throw new NullPointerException("Wrong aviary number");
        }
    }

    public boolean isSuitable(int aviaryId, AnimalType type) {
        Aviary aviary = aviaryRepository.getById(aviaryId);
        if (aviary != null) {
            return (aviary.getType().equals(type) || aviary.getType().equals(AnimalType.ALL))
                    && aviary.getSpaceAvailable() >= type.getSize();
        } else {
            throw new NullPointerException("Wrong aviary number");
        }
    }

}
