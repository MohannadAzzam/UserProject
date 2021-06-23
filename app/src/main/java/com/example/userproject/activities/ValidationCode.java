package com.example.userproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.userproject.R;

public class ValidationCode extends AppCompatActivity {
    EditText enterValedashen;
    Button btn_valed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_code);
        btn_valed=findViewById(R.id.Validation_cod_button);
        btn_valed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ValidationCode.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}