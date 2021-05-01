package com.example.userproject.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.R;
import com.example.userproject.adapters.OrderAdapter;
import com.example.userproject.models.Order;
import com.example.userproject.interfaces.onRecyclerViewItemClickListener;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView orderRecycler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        orderRecycler = findViewById(R.id.category_ReycelerView);

         ArrayList<Order> orders =new ArrayList<>();
         orders.add(new Order(R.drawable.apple,6,"تم","10.10.2020"));
         orders.add(new Order(R.drawable.apple,155,"تم","10.10.2020"));
         orders.add(new Order(R.drawable.apple,155,"تم","10.10.2020"));
         orders.add(new Order(R.drawable.apple,155,"تم","10.10.2020"));
         orders.add(new Order(R.drawable.apple,155,"تم","10.10.2020"));
         orders.add(new Order(R.drawable.apple,155,"تم","10.10.2020"));
        OrderAdapter orderAdapter = new OrderAdapter(orders, new OrderAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent =new Intent(getBaseContext(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        orderRecycler.setLayoutManager(layoutManager);
        orderRecycler.setAdapter(orderAdapter);

    }

}