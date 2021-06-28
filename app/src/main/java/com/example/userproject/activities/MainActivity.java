package com.example.userproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.userproject.R;

import com.example.userproject.fragments.HomeFragment;
import com.example.userproject.fragments.MapFragment;
import com.example.userproject.fragments.Order_custom_fragment;
import com.example.userproject.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView main_bottomNavigationView;
    private FloatingActionButton fab;

    Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        fab = findViewById(R.id.main_cart_fab);

        setSupportActionBar(toolbar);



        main_bottomNavigationView = findViewById(R.id.main_bottom_navigationview);
        main_bottomNavigationView.setBackground(null);
        main_bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        main_bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        getSupportActionBar().setTitle("الرئيسية");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),Cart.class);
                startActivity(intent);
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.bottom_nav_home:
                            selectedFragment = new HomeFragment();
                            toolbar.setTitle("الرئيسية");
                            break;
                        case R.id.bottom_nav_order:
                            selectedFragment = new Order_custom_fragment();
                            toolbar.setTitle("الطلبات");
                            break;
                        case R.id.bottom_nav_cart:
//                            selectedFragment = new ShoppingCartFragment();
//                            toolbar.setTitle("السلة");
                            break;
                        case R.id.bottom_nav_location:
                            selectedFragment = new MapFragment();
                            toolbar.setTitle("الموقع");
                            break;
                        case R.id.bottom_nav_settings:
                            selectedFragment = new SettingsFragment();
                            toolbar.setTitle("الإعدادات");
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_menu, menu);
//        SearchView searchView = (SearchView) menu.findItem(R.id.main_search).getActionView();
//        searchView.setSubmitButtonEnabled(true);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                //when click ok searching....
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //when typing searching...
//                return false;
//            }
//        });
//        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                //when click close searching...
//                return false;
//            }
//        });
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
}