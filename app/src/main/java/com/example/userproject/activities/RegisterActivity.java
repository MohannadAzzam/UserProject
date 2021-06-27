package com.example.userproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.userproject.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout  et_email, et_password, et_address, et_username;
    TextInputEditText et_phoneNumber;
    private Button btn_register;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private AutoCompleteTextView actv_userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        et_username = findViewById(R.id.register_et_username);
        et_phoneNumber = findViewById(R.id.register_et_phoneNumber);
        et_password = findViewById(R.id.register_et_password);

        progressBar = findViewById(R.id.progressBar);
        btn_register = findViewById(R.id.Register_btn_login);

        actv_userType = findViewById(R.id.register_dropDown_userType);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_phoneNumber.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "أدخل رقم الهاتف", Toast.LENGTH_SHORT).show();
                } else {
                progressBar.setVisibility(View.VISIBLE);
                btn_register.setVisibility(View.INVISIBLE);

                    String phone = et_phoneNumber.getText().toString().trim();

                    Log.d("phone", "onClick: " + phone);

                    PhoneAuthOptions options =
                            PhoneAuthOptions.newBuilder(mAuth)
                                    .setPhoneNumber("+97"+phone)
                                    .setTimeout(60L, TimeUnit.SECONDS)
                                    .setActivity(RegisterActivity.this)
                                    .setCallbacks(mCallbacks)
                                    .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);

                }
            }
        });
        String[] option = {"مستخدم", "مزارع"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.option_item, option);

        actv_userType.setText(arrayAdapter.getItem(0).toString().trim(), false);
        actv_userType.setAdapter(arrayAdapter);


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(RegisterActivity.this, "onVerificationCompleted", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                btn_register.setVisibility(View.VISIBLE);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                btn_register.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                progressBar.setVisibility(View.GONE);
                btn_register.setVisibility(View.VISIBLE);
                Toast.makeText(RegisterActivity.this, "تم إرسال رمز التحقق", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, ValidationCode.class);
                intent.putExtra("verificationCredentials", s);
                startActivity(intent);
                finish();
            }

        };
    }

}
