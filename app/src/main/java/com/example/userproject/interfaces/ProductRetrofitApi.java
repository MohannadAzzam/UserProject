package com.example.userproject.interfaces;

import com.example.userproject.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductRetrofitApi {
    @GET("product/")
    Call<List<Product>> getAllProducts();
}
