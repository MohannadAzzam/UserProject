package com.example.userproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.userproject.R;

public class RefuseCauseActivity extends AppCompatActivity {


    private Button btn_refuse;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuse_cause2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        CharSequence sequence = "سبب الرفض";
//        toolbar.setTitle(getString(R.string.Addproduct));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_refuse = findViewById(R.id.addProduct_refuse);
        btn_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}