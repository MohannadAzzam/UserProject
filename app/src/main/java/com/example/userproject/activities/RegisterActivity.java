package com.example.userproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.userproject.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout et_username, et_phoneNumber, et_email, et_password, et_address;
    private Button btn_register;

    private AutoCompleteTextView actv_userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);


        et_username = findViewById(R.id.register_et_username);
        et_phoneNumber = findViewById(R.id.register_et_phoneNumber);
        et_password = findViewById(R.id.register_et_password);
        et_email = findViewById(R.id.register_et_email);
        et_address = findViewById(R.id.register_et_address);

        btn_register = findViewById(R.id.login_btn_login);

        actv_userType = findViewById(R.id.register_dropDown_userType);
        String[] option = {"مستخدم", "مزارع"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.option_item, option);

        actv_userType.setText(arrayAdapter.getItem(0).toString().trim(), false);
        actv_userType.setAdapter(arrayAdapter);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "تم انشاء حساب جديد", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}