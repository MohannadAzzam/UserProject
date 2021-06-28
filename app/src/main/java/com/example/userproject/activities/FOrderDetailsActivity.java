package com.example.userproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.userproject.R;

public class FOrderDetailsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btn_refuse ,btn_accept;
    private TextView name , addres, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_order_details);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setTitle("تفاصيل الطلب");
//        getSupportActionBar().setTitle("تفاصيل الطلب");

        btn_refuse = findViewById(R.id.orderDetails_refuse);
        btn_accept = findViewById(R.id.orderDetails_accept);
        name = findViewById(R.id.orderDetails_price);
        addres = findViewById(R.id.orderDetails_adress);
        phone = findViewById(R.id.orderDetails_phonenummer);

        Intent i = getIntent();

        String  namee= i.getStringExtra("name");
        String address= i.getStringExtra("addres");
        String phonee =i.getStringExtra("phone");


        name.setText(namee);
        addres.setText(address);
        phone.setText(phonee);


        btn_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),RefuseCauseActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}