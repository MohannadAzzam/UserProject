package com.example.userproject.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.userproject.DB.DB_Product;
import com.example.userproject.R;
import com.example.userproject.models.FProduct;
import com.google.android.material.textfield.TextInputEditText;

public class FEditProduct extends AppCompatActivity {
    Toolbar toolbar;
    Button btn_add;

    private static final int PICK_FROM_GALLERY = 1;


    String imagUi;
    TextInputEditText name, price, desc;

    int id ;

    ImageView imag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_edit_product);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("أضف منتج");

        btn_add = findViewById(R.id.EditProduct_add);
        imag = findViewById(R.id.Edit_product_iv);
        name = findViewById(R.id.EditProduct_et_productName);
        price = findViewById(R.id.EditProduct_et_productPrice);
        desc = findViewById(R.id.EditProduct_et_productDes);

        id = getIntent().getIntExtra("id",0);
        final DB_Product db = new DB_Product(this);
        FProduct c = db.getProuct(id);




        if (c != null) {


//        Bundle b =new Bundle();

            name.setText(c.getName());
            price.setText(c.getPrice());
            desc.setText(c.getDesc());
            imag.setImageURI(Uri.parse(c.getImag()));
            imagUi = c.getImag();


//        name.setText(nameb);
//            price.setText(priceb);
//            desc.setText(descb);
//            imag.setImageResource(Integer.parseInt(imagb));
//            imagUi = c.getImag();

        }
//        }

        imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String namee = name.getText().toString();
                String pricee = price.getText().toString();
                String descc = desc.getText().toString();
//
                if(namee.isEmpty()||namee.equals(" ")){
                    name.setError("يجب تعبئة الحقل");
                    return;
                }
                else if(pricee.isEmpty()||pricee.equals(" ")) {
                    price.setError("يجب تعبئة الحقل");
                    return;
                }else if(descc.isEmpty()||descc.equals(" ")) {
                    desc.setError("يجب تعبئة الحقل");
                    return;
                }

                FProduct p = new FProduct(namee, pricee, descc, imagUi);
//
                DB_Product dbProduct = new DB_Product(getApplicationContext());

                dbProduct.insertProdcut(p);

                boolean res = db.updateProdcut(p);
                if (res){
                    Toast.makeText(getBaseContext(), "Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
//                    Toast.makeText(EditProduct.this, "not edit", Toast.LENGTH_SHORT).show();

                }

                finish();


//                Bundle b = new Bundle();
//                b.putInt("id",p.getId());
//
//
//                FragmentManager fmm = getSupportFragmentManager();
//                FragmentTransaction ftt = fmm.beginTransaction();
//                DaylogFragment fragmentt = new DaylogFragment();
//                fragmentt.setArguments(b);
//                ftt.addToBackStack(null);
//                ftt.commit();

//                Intent i = new Intent(getApplicationContext(),HomeFragment.class);
//                startActivity(i);
//                startActivityFromFragment(HomeFragment.class,i,1);;


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            Uri u = data.getData();

            int flag = data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION |
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            getContentResolver().takePersistableUriPermission(u, flag);
            imag.setImageURI(u);
            imagUi = u.toString();


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case PICK_FROM_GALLERY:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }
}