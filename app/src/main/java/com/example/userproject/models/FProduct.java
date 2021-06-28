package com.example.userproject.models;

public class FProduct {
    private  int id ;
    private  String  name ;
    private  String  price ;
    private  String  desc ;
    private  String  imag ;

    public FProduct(int id, String name, String price, String desc, String imag) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.imag = imag;
    }

    public FProduct(String name, String price, String desc, String imag) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.imag = imag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImag() {
        return imag;
    }

    public void setImag(String imag) {
        this.imag = imag;
    }
}
