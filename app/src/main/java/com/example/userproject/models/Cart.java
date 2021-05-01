package com.example.userproject.models;

public class Cart {
    private int CartId;
    private int productImage;
    private int productPrice;
    private String productName;

    public Cart(int productImage, int productPrice, String productName) {
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productName = productName;
    }

    public int getCartId() {
        return CartId;
    }

    public void setCartId(int cartId) {
        CartId = cartId;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
