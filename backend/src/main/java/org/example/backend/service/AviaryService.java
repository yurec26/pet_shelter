package org.example.backend.service;

import org.example.backend.constants.AnimalType;
import org.example.backend.constants.AviaryConstants;
import org.example.backend.entity.Animal;
import org.example.backend.entity.Aviary;
import org.example.backend.repository.AviaryRepository;

import java.io.IOException;
import java.util.List;

public class AviaryService {
    private final AviaryRepository aviaryRepository;
    private final int ADOPT_AVIARY_NUM = 9999;

    public int getADOPT_AVIARY_NUM() {
        return ADOPT_AVIARY_NUM;
    }

    public AviaryService(AviaryRepository aviaryRepository) {
        this.aviaryRepository = aviaryRepository;
    }

    public List<Aviary> getAll() throws IOException {
        return aviaryRepository.loadFromRepo().getAll();
    }

    public void move(Animal animal, int newAviaryNum) throws IOException {
        Aviary newAviary = aviaryRepository.getById(newAviaryNum);
        if (newAviary != null) {
            Aviary oldAviary = aviaryRepository.getById(animal.getAviaryNum());
            if (newAviaryNum != ADOPT_AVIARY_NUM) {
                newAviary.setSpaceAvailable(newAviary.getSpaceAvailable() - animal.getType().getSize());
            }
            oldAviary.setSpaceAvailable(oldAviary.getSpaceAvailable() + animal.getType().getSize());
            oldAviary.getAnimalList().remove(Integer.valueOf(animal.getId()));
            if (oldAviary.getAnimalList().isEmpty()) {
                oldAviary.setType(AnimalType.ALL);
            }
            if (newAviary.getAnimalList().isEmpty() && newAviary.getId()!= AviaryConstants.ADOPT_AVIARY_NUM) {
                newAviary.setType(animal.getType());
            }
            newAviary.getAnimalList().add(animal.getId());
            aviaryRepository.update(newAviary).update(oldAviary).updateRepoFile();
        } else {
            throw new NullPointerException("Wrong aviary number");
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
            aviaryRepository.update(aviary).updateRepoFile();
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
