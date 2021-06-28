package com.example.userproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.R;
import com.example.userproject.adapters.FarmAdapter;
import com.example.userproject.models.Farm;


import java.util.ArrayList;

public class Farm_details extends AppCompatActivity {

    ImageView cart_iv, farmer_avatar;
    Toolbar toolbar;
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_details);

        rv = findViewById(R.id.farm_details_rv);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("تفاصيل البائع");
//        farmer_avatar = findViewById(R.id.farmer_avatar);

        cart_iv = findViewById(R.id.farm_details_cart);
        cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Cart.class);
                startActivity(intent);
            }
        });

        ArrayList<Farm> farms = new ArrayList<>();
        farms.add(new Farm(R.drawable.apple,6,"تفاح","10.10.2020"));
        farms.add(new Farm(R.drawable.apple,4,"تفاح","10.10.2020"));
        farms.add(new Farm(R.drawable.apple,5,"تفاح","10.10.2020"));
        farms.add(new Farm(R.drawable.apple,8,"تفاح","10.10.2020"));
        farms.add(new Farm(R.drawable.apple,9,"تفاح","10.10.2020"));

        FarmAdapter farmAdapter = new FarmAdapter(farms, new FarmAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent(getBaseContext(),ProductDetailsActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());

        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.setAdapter(farmAdapter);
    }
}