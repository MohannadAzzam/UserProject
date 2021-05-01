package com.example.userproject.models;

public class Person {
    private int personId;
    private int personImage;

    public Person(int personImage) {
        this.personImage = personImage;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getPersonImage() {
        return personImage;
    }

    public void setPersonImage(int personImage) {
        this.personImage = personImage;
    }
}
