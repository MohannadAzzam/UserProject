package com.example.userproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.userproject.R;

public class FAddProductActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btn_add;
    private AutoCompleteTextView et_productType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_add_product);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("أضف منتج");
        et_productType = findViewById(R.id.addproduct_et_productType);

        final String[] items = new String[]{
                "فاكهة",
                "خضار"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getBaseContext(),
                R.layout.custom_dropdown_item,
                items
        );
        et_productType.setAdapter(adapter);


        btn_add = findViewById(R.id.addProduct_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}