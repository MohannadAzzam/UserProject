package com.example.userproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userproject.DB.DB_Product;
import com.example.userproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFramgent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFramgent extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView tv_num_product;


    public DashboardFramgent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFramgent.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFramgent newInstance(String param1, String param2) {
        DashboardFramgent fragment = new DashboardFramgent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        tv_num_product=v.findViewById(R.id.dash_number);


        DB_Product db = new DB_Product(getContext());

        long countt = db.getProductCount();

        tv_num_product.setText(countt+"");


        return v;
    }
}