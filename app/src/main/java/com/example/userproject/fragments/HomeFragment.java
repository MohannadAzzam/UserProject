package com.example.userproject.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.userproject.activities.CategoryActivity;
import com.example.userproject.activities.Farm_details;
import com.example.userproject.activities.ProductDetailsActivity;
import com.example.userproject.R;
import com.example.userproject.adapters.CategoryAdapter;
import com.example.userproject.adapters.PersonAdapter;
import com.example.userproject.adapters.ProductAdapter;
import com.example.userproject.interfaces.CategoryRetrofitApi;
import com.example.userproject.interfaces.ProductRetrofitApi;
import com.example.userproject.models.Category;
import com.example.userproject.models.Person;
import com.example.userproject.models.Product;
import com.example.userproject.interfaces.onRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    //Components Deceleration
    private RecyclerView best_pricely_locationaly_rv;
    private RecyclerView nearly_product_rv;
    private RecyclerView category_rv;
    private RecyclerView farmer_rv;
    private SwipeRefreshLayout swipeRefreshLayout;


    private List<Category> categoriesList = new ArrayList<>();
    private List<Product> productsList = new ArrayList<>();
    private CategoryRetrofitApi categoryRetrofitApi;
    private ProductRetrofitApi productRetrofitApi;
    private ArrayList<Product> products;
    private ArrayList<Person> people;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        best_pricely_locationaly_rv = v.findViewById(R.id.fragment_home_best_pricely_locationaly_rv);
        nearly_product_rv = v.findViewById(R.id.fragment_home_nearly_product_rv);
        category_rv = v.findViewById(R.id.fragment_home_category_rv);
        farmer_rv = v.findViewById(R.id.fragment_home_nearly_seller_rv);
        swipeRefreshLayout = v.findViewById(R.id.home_swipeToRefresh);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//
//        if (!isConnected(HomeFragment.this)){
//            showCustomDialog();
//        }else {

        products = new ArrayList<>();
        products.add(new Product(R.drawable.apple, "تفاح", "20 شيكل", "غزة - الزيتون - دوار حبيب"));
        products.add(new Product(R.drawable.apple, "تفاح", "20 شيكل", "غزة - الزيتون - دوار حبيب"));
        products.add(new Product(R.drawable.apple, "تفاح", "20 شيكل", "غزة - الزيتون - دوار حبيب"));
        products.add(new Product(R.drawable.apple, "تفاح", "20 شيكل", "غزة - الزيتون - دوار حبيب"));
        products.add(new Product(R.drawable.apple, "تفاح", "20 شيكل", "غزة - الزيتون - دوار حبيب"));

        people = new ArrayList<>();
        people.add(new Person(R.drawable.person));
        people.add(new Person(R.drawable.apple));
        people.add(new Person(R.drawable.ic_person));
        people.add(new Person(R.drawable.person));
        people.add(new Person(R.drawable.apple));
        people.add(new Person(R.drawable.ic_person));
        people.add(new Person(R.drawable.person));
        people.add(new Person(R.drawable.apple));
        people.add(new Person(R.drawable.ic_person));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mohannadazzam-001-site1.itempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        categoryRetrofitApi = retrofit.create(CategoryRetrofitApi.class);
        productRetrofitApi = retrofit.create(ProductRetrofitApi.class);

        getBestPricely(products);
        getNearly(products);
        getNearFarmer(people);
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("فواكه", R.drawable.apple));
        categories.add(new Category("خضار", R.drawable.apple));
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categories, new CategoryAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager CategoryLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        category_rv.setLayoutManager(CategoryLayoutManager);
        category_rv.setAdapter(categoryAdapter);
//        getAllCategories();
        //getAllProducts();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBestPricely(products);
                getNearly(products);
                getNearFarmer(people);
//                ArrayList<Product> products2 = new ArrayList<>();
//                products2.add(new Product(R.drawable.apple, "تفاح", "20 شيكل", "غزة - الزيتون - دوار حبيب"));
//                products2.add(new Product(R.drawable.apple, "تفاح", "20 شيكل", "غزة - الزيتون - دوار حبيب"));
//                products2.add(new Product(R.drawable.apple, "تفاح", "20 شيكل", "غزة - الزيتون - دوار حبيب"));
//                products2.add(new Product(R.drawable.apple, "تفاح", "20 شيكل", "غزة - الزيتون - دوار حبيب"));
//                products2.add(new Product(R.drawable.apple, "تفاح", "20 شيكل", "غزة - الزيتون - دوار حبيب"));
//                getAllCategories();
                //getAllProducts();
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("فواكه", R.drawable.apple));
        categories.add(new Category("خضار", R.drawable.apple));
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categories, new CategoryAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager CategoryLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        category_rv.setLayoutManager(CategoryLayoutManager);
        category_rv.setAdapter(categoryAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getBestPricely(ArrayList<Product> products) {
        ProductAdapter productAdapter = new ProductAdapter(products, new ProductAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        best_pricely_locationaly_rv.setLayoutManager(layoutManager);
        best_pricely_locationaly_rv.setHasFixedSize(true);
        best_pricely_locationaly_rv.setAdapter(productAdapter);
    }

    private void getNearly(ArrayList<Product> products) {
        ProductAdapter productAdapter = new ProductAdapter(products, new ProductAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        nearly_product_rv.setLayoutManager(layoutManager2);
        nearly_product_rv.setHasFixedSize(true);
        nearly_product_rv.setAdapter(productAdapter);
    }

    private void getNearFarmer(ArrayList<Person> people) {
        PersonAdapter personAdapter = new PersonAdapter(people, new PersonAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent(getContext(), Farm_details.class);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager personLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        farmer_rv.setHasFixedSize(true);
        farmer_rv.setLayoutManager(personLayoutManager);
        farmer_rv.setAdapter(personAdapter);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
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

  /*  public  void getAllProducts(){
        Call<List<Product>> products = productRetrofitApi.getAllProducts();
        products.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    String message = "Response successful";
                    Toast.makeText(getContext(), "" + message, Toast.LENGTH_LONG).show();
                    productsList = (ArrayList<Product>) response.body();
                    ProductAdapter productAdapter = new ProductAdapter(getContext(), (ArrayList<Product>) productsList, new onRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(String id) {
                            Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                            startActivity(intent);
                        }
                    });
                    RecyclerView.LayoutManager ProductLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                    best_pricely_locationaly_rv.setLayoutManager(ProductLayoutManager);
                    best_pricely_locationaly_rv.setAdapter(productAdapter);
                }else {
                    String message = "An error occurred try again later ..";
                    Toast.makeText(getContext(), "" + message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                String message = t.getLocalizedMessage();
               // Toast.makeText(getContext(), "No Categories \n" + message, Toast.LENGTH_LONG).show();
            }
        });
    }*/
//    public void getAllCategories() {
//        Call<List<Category>> categories = categoryRetrofitApi.getAllCategories();
//        categories.enqueue(new Callback<List<Category>>() {
//            @Override
//            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
//                if (response.isSuccessful()) {
//                    String message = "Response successful";
//                    Toast.makeText(getContext(), "" + message, Toast.LENGTH_LONG).show();
//                    categoriesList = (ArrayList<Category>) response.body();
//                    CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), (ArrayList<Category>) categoriesList, new onRecyclerViewItemClickListener() {
//                        @Override
//                        public void onItemClick(String id) {
//                            Intent intent = new Intent(getContext(), CategoryActivity.class);
//                            startActivity(intent);
//                        }
//                    });
//                    RecyclerView.LayoutManager CategoryLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//                    category_rv.setLayoutManager(CategoryLayoutManager);
//                    category_rv.setAdapter(categoryAdapter);
//                } else {
//                    String message = "An error occurred try again later ..";
//                    Toast.makeText(getContext(), "" + message, Toast.LENGTH_LONG).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<List<Category>> call, Throwable t) {
//                String message = t.getLocalizedMessage();
//                Toast.makeText(getContext(), "No Categories \n" + message, Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    boolean isConnected(HomeFragment login) {
        ConnectivityManager connectivityManager = (ConnectivityManager) login.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo MobileDataConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (wifiConnection != null && wifiConnection.isConnected() || MobileDataConnection != null && MobileDataConnection.isConnected()) {
            return true;
        } else {
            return false;
        }

    }

    void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("يرجى الاتصال بالانترنت.").setCancelable(false)
                .setPositiveButton("الاتصال", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent = new Intent(getContext(), Settings.ACTION_WIFI_SETTINGS.getClass());
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//                        startActivity(intent);
                    }
                })
                .setNegativeButton("إالغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "الرجاء الاتصال بالانترنت لعرض المحتوى", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        builder.show();
    }

}