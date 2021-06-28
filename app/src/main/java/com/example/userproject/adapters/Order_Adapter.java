package com.example.userproject.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.R;
import com.example.userproject.models.FOrder;

import java.util.ArrayList;

public class Order_Adapter extends RecyclerView.Adapter<Order_Adapter.VH> {

    ArrayList<FOrder> arrayList;
    private Order_Adapter.MyAdapterListener myAdapterListener;

    Context context;



    public Order_Adapter(ArrayList<FOrder> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public interface MyAdapterListener {
        void onContainerClick( FOrder order );
    }

    public void setMyAdapterListener(Order_Adapter.MyAdapterListener myAdapterListener) {
        this.myAdapterListener = myAdapterListener;
    }

    public Order_Adapter.MyAdapterListener getMyAdapterListener() {
        return myAdapterListener;
    }

    public void  setOrder(ArrayList<FOrder> order){
        this.arrayList = order;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_orders, parent,false);
        VH holder = new VH(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        FOrder order = arrayList.get(position);

        holder.name.setText(order.getName());
        holder.addres.setText(String.valueOf(order.getAddress()));
        holder.phone.setText(String.valueOf(order.getPhone()));
        holder.imag.setImageURI(Uri.parse(order.getImag()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myAdapterListener.onContainerClick(order);

            }
        });
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class VH extends RecyclerView.ViewHolder {

        TextView name , addres, phone;
        ImageView   imag;

        public VH(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.order_username_tv);
            addres=itemView.findViewById(R.id.order_address_tv);
            phone=itemView.findViewById(R.id.order_phonenumber_tv);
            imag=itemView.findViewById(R.id.farmer_order_iv);

        }
    }
}


