package com.example.userproject.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.userproject.R;
import com.example.userproject.activities.ProductDetailsActivity;
import com.example.userproject.adapters.OrderAdapter;
import com.example.userproject.models.Order;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Order_custom_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Order_custom_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView orderRecycler;
    private ArrayList<Order> orders;
    private SwipeRefreshLayout swipeRefreshLayout;

    public Order_custom_fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Order_custom_fragment newInstance(String param1, String param2) {
        Order_custom_fragment fragment = new Order_custom_fragment();
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
        View v =  inflater.inflate(R.layout.fragment_order_custom_fragment, container, false);
        setHasOptionsMenu(true);
        orderRecycler = v.findViewById(R.id.orderfragment_rv_orders);
        swipeRefreshLayout = v.findViewById(R.id.order_swipeToRefresh);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
         orders =new ArrayList<>();
        orders.add(new Order(R.drawable.apple,6,"تم","10.10.2020"));
        orders.add(new Order(R.drawable.apple,155,"تم","10.10.2020"));
        orders.add(new Order(R.drawable.apple,155,"تم","10.10.2020"));
        orders.add(new Order(R.drawable.apple,155,"تم","10.10.2020"));
        orders.add(new Order(R.drawable.apple,155,"تم","10.10.2020"));
        orders.add(new Order(R.drawable.apple,155,"تم","10.10.2020"));
        getAllProduct(orders);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllProduct(orders);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
    private void getAllProduct(ArrayList<Order> orders) {
        OrderAdapter orderAdapter = new OrderAdapter(orders, new OrderAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent =new Intent(getContext(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        orderRecycler.setLayoutManager(layoutManager);
        orderRecycler.setHasFixedSize(true);
        orderRecycler.setAdapter(orderAdapter);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.main_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //when click ok searching....
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //when typing searching...
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //when click close searching...
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}