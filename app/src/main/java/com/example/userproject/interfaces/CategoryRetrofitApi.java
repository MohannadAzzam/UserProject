package com.example.userproject.interfaces;


import com.example.userproject.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryRetrofitApi {

    @GET("Category/")
    Call<List<Category>> getAllCategories();
}
