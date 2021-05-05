package com.example.userproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.userproject.R;
import com.example.userproject.adapters.OrderAdapter;
import com.example.userproject.models.Order;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView orderRecycler;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Toolbar toolbar;
    private ArrayList<Order> orders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        orderRecycler = findViewById(R.id.category_ReycelerView);
        swipeRefreshLayout = findViewById(R.id.category_swipeToRefresh);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("المنتجات");

        orders = new ArrayList<>();
        orders.add(new Order(R.drawable.apple, 6, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 6, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 6, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));
        orders.add(new Order(R.drawable.apple, 155, "تم", "10.10.2020"));

        getAllProduct(orders);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllProduct(orders);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getAllProduct(ArrayList<Order> orders) {
        OrderAdapter orderAdapter = new OrderAdapter(orders, new OrderAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent(getBaseContext(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        orderRecycler.setLayoutManager(layoutManager);
        orderRecycler.setAdapter(orderAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.main_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //when click ok searching....
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //when typing searching...
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //when click close searching...
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}