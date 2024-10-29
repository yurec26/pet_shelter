package org.example.backend.entity;

import org.example.backend.constants.AnimalType;

import java.util.List;
import java.util.Objects;

public class Aviary {

    private int id;
    private double capacity;
    private AnimalType type;
    private List<Integer> animalList;
    private double spaceAvailable;

    public Aviary() {
    }

    public Aviary(int id, double capacity, AnimalType type, List<Integer> animalList) {
        this.id = id;
        this.capacity = capacity;
        this.type = type;
        this.animalList = animalList;
        this.spaceAvailable = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCapacity() {
        return capacity;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public List<Integer> getAnimalList() {
        return animalList;
    }

    public double getSpaceAvailable() {
        return spaceAvailable;
    }

    public void setSpaceAvailable(double spaceAvailable) {
        this.spaceAvailable = spaceAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aviary aviary)) return false;
        return id == aviary.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Aviary{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", type=" + type +
                ", animalList=" + animalList +
                ", spaceAvailable=" + spaceAvailable +
                '}';
    }
}
