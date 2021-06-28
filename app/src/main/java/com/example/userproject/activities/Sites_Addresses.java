package com.example.userproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.R;
import com.example.userproject.adapters.AddressAdapter;

import com.example.userproject.models.UserAddress;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Sites_Addresses extends AppCompatActivity {

    private FloatingActionButton fab;
    private Toolbar toolbar;

    RecyclerView addressRecycler ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites__addresses);

        addressRecycler =findViewById(R.id.Recycler_Address);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("الأماكن المحفوظة");

        fab = findViewById(R.id.Sites_addresses_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<UserAddress> addresses =new ArrayList<>();

        addresses.add(new UserAddress(R.drawable.apple, "Gaza"));
        addresses.add(new UserAddress(R.drawable.apple, "Gaza"));
        addresses.add(new UserAddress(R.drawable.apple, "Gaza"));
        addresses.add(new UserAddress(R.drawable.apple, "Gaza"));

        AddressAdapter addressAdapter = new AddressAdapter(getBaseContext(), addresses, new AddressAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int id) {

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        addressRecycler.setLayoutManager(layoutManager);
        addressRecycler.setAdapter(addressAdapter);

    }
}