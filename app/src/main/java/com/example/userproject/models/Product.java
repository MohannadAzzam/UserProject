package com.example.userproject.models;

public class Product {

    private int productId;
    private int productImage;
    private String productName;
    private String productPrice;
    private String productAddress;

    public Product(int productId, int productImage, String productName, String productPrice, String productAddress) {
        this.productId = productId;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAddress = productAddress;
    }

    public Product(int productImage, String productName, String productPrice, String productAddress) {
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAddress = productAddress;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductAddress() {
        return productAddress;
    }

    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress;
    }
}
