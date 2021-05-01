package com.example.userproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.R;
import com.example.userproject.adapters.CartAdapter;
import com.example.userproject.fragments.DailogFragment;
import com.example.userproject.fragments.MapFragment;
import com.example.userproject.interfaces.onRecyclerViewItemClickListener;

import java.util.ArrayList;

public class Cart extends AppCompatActivity implements DailogFragment.DialogeInterface {
    Button btn_Approval;
    Toolbar toolbar;
    private RecyclerView cart_rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart1);

        cart_rv = findViewById(R.id.cart_order_rv);

        btn_Approval =findViewById(R.id.cart_id_btn_Approval);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("سلة المشتريات");
        btn_Approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
DailogFragment dailogFragment=DailogFragment.newInstance("Dialog message","Dialog done",123);
dailogFragment.show(getSupportFragmentManager(),null);
            }
        });


        ArrayList<com.example.userproject.models.Cart> carts = new ArrayList<>();
        carts.add(new com.example.userproject.models.Cart(R.drawable.apple,25,"تفاح"));
        carts.add(new com.example.userproject.models.Cart(R.drawable.apple,25,"تفاح"));
        carts.add(new com.example.userproject.models.Cart(R.drawable.apple,25,"تفاح"));
        carts.add(new com.example.userproject.models.Cart(R.drawable.apple,25,"تفاح"));
        carts.add(new com.example.userproject.models.Cart(R.drawable.apple,25,"تفاح"));

        CartAdapter cartAdapter = new CartAdapter(carts, new onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(String id) {

            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(),1);

        cart_rv.setLayoutManager(layoutManager);
        cart_rv.setHasFixedSize(true);
        cart_rv.setAdapter(cartAdapter);
    }


    @Override
    public void DialogeInterface() {
        Intent intent= new Intent(getBaseContext(), MapFragment.class);
        startActivity(intent);
    }
}