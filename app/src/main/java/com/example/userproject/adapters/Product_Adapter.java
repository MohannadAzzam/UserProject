package com.example.userproject.adapters;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.R;
import com.example.userproject.models.FProduct;

import java.util.ArrayList;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.VH> {

    ArrayList<FProduct> arrayList;
//    abapter_and_main listener;

    private MyAdapterListener myAdapterListener;

    Context context;

    public Product_Adapter(ArrayList<FProduct> arrayList , Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }


    public  void addProduct(FProduct product){

        arrayList.add(product);
        notifyDataSetChanged();
    }

    public interface MyAdapterListener {
        void onContainerClick( FProduct product );
    }

    public void setMyAdapterListener(MyAdapterListener myAdapterListener) {
        this.myAdapterListener = myAdapterListener;
    }

    public void  setProdact(ArrayList<FProduct> prodact){
        this.arrayList = prodact;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_homerv_product, parent,false);
        VH holder = new VH(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        FProduct product = arrayList.get(position);

        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice());
        holder.desc.setText(product.getDesc());
        if (!TextUtils.isEmpty(product.getImag())){


            holder.imag.setImageURI(Uri.parse(product.getImag()));

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myAdapterListener.onContainerClick(product);

                }
            });


        }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class VH extends RecyclerView.ViewHolder {

        TextView name , price, desc;
        ImageView   imag;

        public VH(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.farmer_order_name);
            price=itemView.findViewById(R.id.farmer_order_price);
            desc=itemView.findViewById(R.id.farmer_order_date);
            imag=itemView.findViewById(R.id.farmer_order_iv);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    myAdapterListener.onContainerClick(prod);
//
//                }
//            });

        }
    }
}


