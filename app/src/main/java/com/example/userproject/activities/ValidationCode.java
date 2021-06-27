package com.example.userproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.userproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ValidationCode extends AppCompatActivity {


    Button verify_btn;
    TextInputEditText validationCode_et;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private String verificationCodeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_validation_code);

        verify_btn = findViewById(R.id.ValidationCode_btn_ok_);
        validationCode_et = findViewById(R.id.ValidationCode_et);
        progressBar = findViewById(R.id.progressBar);


        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        verificationCodeId = getIntent().getStringExtra("verificationCredentials");


        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String otp = validationCode_et.getText().toString().trim();

                if (otp.isEmpty()) {
                    Toast.makeText(ValidationCode.this, "اكتب رمز التحقق", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    verify_btn.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeId, otp);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ValidationCode.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(ValidationCode.this, "حدث خطأ ما", Toast.LENGTH_SHORT).show();                            }
                        }
                        progressBar.setVisibility(View.GONE);
                        verify_btn.setVisibility(View.VISIBLE);
                    }

                });
    }
}