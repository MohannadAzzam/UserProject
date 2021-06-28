package com.example.userproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.userproject.R;
import com.example.userproject.adapters.Product_Adapter;
import com.example.userproject.models.FProduct;

import java.util.ArrayList;

public class R_Home_Edit_Delete extends AppCompatActivity {


    ImageView imageView;

    TextView name , price, desc;
    Toolbar toolbar;

    int id;

    private static final int PICK_FROM_GALLERY = 1;

    FProduct product;
    Product_Adapter product_adapter;
    ArrayList<FProduct> products = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r__home__edit__delete);
    }
}