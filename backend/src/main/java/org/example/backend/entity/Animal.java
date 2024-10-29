package org.example.backend.entity;

import org.example.backend.constants.AnimalType;

import java.time.LocalDate;
import java.util.*;

public class Animal {

    private int id;
    private AnimalType type;
    private String name;
    private String breed;
    private LocalDate birthDate;
    private LocalDate incomeDate;
    private LocalDate adoptDate;
    private Owner owner;
    private Set<String> vaccList = new HashSet<>();
    private Set<String> desList = new HashSet<>();
    private boolean isAdopted;
    private int aviaryNum;
    private String description;

    public Animal() {
    }

    public Animal(AnimalType type, String name, String breed, LocalDate birthDate, int aviaryNum) {
        this.type = type;
        this.name = name;
        this.breed = breed;
        this.birthDate = birthDate;
        this.aviaryNum = aviaryNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }

    public LocalDate getAdoptDate() {
        return adoptDate;
    }

    public void setAdoptDate(LocalDate adoptDate) {
        this.adoptDate = adoptDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Set<String> getVaccList() {
        return vaccList;
    }

    public Set<String> getDesList() {
        return desList;
    }

    public boolean isAdopted() {
        return isAdopted;
    }

    public void setAdopted(boolean adopted) {
        isAdopted = adopted;
    }

    public int getAviaryNum() {
        return aviaryNum;
    }

    public void setAviaryNum(int aviaryNum) {
        this.aviaryNum = aviaryNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return id == animal.id && Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", birthDate=" + birthDate +
                ", incomeDate=" + incomeDate +
                ", adoptDate=" + adoptDate +
                ", owner=" + owner +
                ", illList=" + vaccList +
                ", desList=" + desList +
                ", isAdopted=" + isAdopted +
                ", aviaryNum=" + aviaryNum +
                ", description='" + description + '\'' +
                '}';
    }
}