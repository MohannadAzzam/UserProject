package com.example.userproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.userproject.R;
import com.example.userproject.fragments.DashboardFramgent;
import com.example.userproject.fragments.FHomeFragment;
import com.example.userproject.fragments.OrderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FMainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView main_bottomNavigationView;
    private FloatingActionButton fab;

    private Fragment selectedFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_main);


        toolbar = findViewById(R.id.main_toolbar);
        fab = findViewById(R.id.main_cart_fab);

        setSupportActionBar(toolbar);

        main_bottomNavigationView = findViewById(R.id.main_bottom_navigationview);
        main_bottomNavigationView.setBackground(null);
        main_bottomNavigationView.getMenu().getItem(3).setEnabled(false);

        main_bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new FHomeFragment()).commit();

        getSupportActionBar().setTitle("الرئيسية");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FAddProductActivity.class);
                startActivity(intent);
                finish();
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
                            selectedFragment = new FHomeFragment();
                            toolbar.setTitle("الرئيسية");
                            break;
                        case R.id.bottom_nav_order:
                            selectedFragment = new OrderFragment();
                            toolbar.setTitle("الطلبات");
                            break;
                        case R.id.bottom_nav_cart:

                            break;
                        case R.id.bottom_nav_dashboard:
                            selectedFragment = new DashboardFramgent();
                            toolbar.setTitle("الإحصائيات");
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_settings:
                Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}