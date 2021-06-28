package com.example.userproject.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.userproject.DB.DB_Product;

public  class Dialog extends DialogFragment {

    int idd;
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        idd = intent.getIntExtra("id", 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to delete the item?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        DB_Product db = new DB_Product(getContext());
                        boolean res = db.deleteProduct(idd);
                        getActivity().finish();

////
//                        if (res) {
//                            Toast.makeText(getContext(), "successful", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getContext(), "not added", Toast.LENGTH_SHORT).show();
//
//                      }
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
