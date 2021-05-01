package com.example.userproject.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.userproject.R;
import com.example.userproject.activities.MainActivity;


public class DailogFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MESSAGE = "maseg ";
    private static final String ARG_TITLE = "titel";
    private static final String ARG_DONE = "Done ";

    private Button done_btn;


    // TODO: Rename and change types of parameters
    private String message;
    private String title;
    private String Done;
    private DialogeInterface dialogeInterface;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof DialogeInterface){
            dialogeInterface=(DialogeInterface) context;
        }else{throw new RuntimeException("gh");}
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogeInterface=null;
    }

    public DailogFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static com.example.userproject.fragments.DailogFragment newInstance(String message, String done, int title) {
        com.example.userproject.fragments.DailogFragment fragment = new com.example.userproject.fragments.DailogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        args.putString(ARG_DONE, done);
        args.putInt(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString(ARG_MESSAGE);
            Done = getArguments().getString(ARG_DONE);
            title = getArguments().getString(ARG_TITLE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_dailog, container, false);
       done_btn =v.findViewById(R.id.button__daulig);
       return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Button btn=view.findViewById(R.id.button__daulig);
        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public interface  DialogeInterface{
        void DialogeInterface(

        );
    }
}