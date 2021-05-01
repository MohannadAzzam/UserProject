package com.example.userproject.models;

public class UserAddress {
  private int id;
  private int imag;
  private  String name;

    public UserAddress(int id, int imag, String name) {
        this.id = id;
        this.imag = imag;
        this.name = name;
    }

    public UserAddress(int imag, String name) {
        this.imag = imag;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImag() {
        return imag;
    }

    public void setImag(int imag) {
        this.imag = imag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
