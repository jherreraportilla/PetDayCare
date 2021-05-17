package com.example.petdaycare;

import java.io.Serializable;

public class Pet implements Serializable {
    String name;
    String breed;
    String weight;
    String gender;

    //Constructor vacio
    public Pet() {
    }
    //Constructor con todos los atributos
    public Pet(String name, String breed, String gender, String weight) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.weight = weight;
    }
    //Getters
    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getGender() {
        return gender;
    }

    public String getWeight() {
        return weight;
    }
    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
