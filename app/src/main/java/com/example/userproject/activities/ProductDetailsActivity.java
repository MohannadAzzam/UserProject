package com.example.userproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.userproject.R;

public class ProductDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView owner_iv_avatar;
    Button addToCart_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        toolbar = findViewById(R.id.product_details_toolbar);
        addToCart_btn = findViewById(R.id.product_details_product_addToCart_btn);
        owner_iv_avatar = findViewById(R.id.product_details_product_owner_iv_avatar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("تفاصيل المنتج");

        owner_iv_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),Farm_details.class);
                startActivity(intent);
            }
        });

        addToCart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetailsActivity.this, "تمت الإضافة ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}