package com.example.userproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.R;
import com.example.userproject.models.UserAddress;


import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {
    private Context context;
    private ArrayList<UserAddress> arrayList;
    private onRecyclerViewItemClickListener listener;

    public AddressAdapter(Context context, ArrayList<UserAddress> arrayList, onRecyclerViewItemClickListener listener) {
        this.context = context;
        this.arrayList = arrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.addresses_custom_design, parent, false);
        return new AddressViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        UserAddress userAddress = arrayList.get(position);
        holder.imag.setImageResource(userAddress.getImag());
        holder.name.setText(userAddress.getName());


        holder.imag.setTag(userAddress.getImag());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class AddressViewHolder extends RecyclerView.ViewHolder {
        ImageView imag;
        TextView name;

        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            imag = itemView.findViewById(R.id.AddressesImageView);
            name = itemView.findViewById(R.id.AddresseName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = (int) imag.getTag();
                    listener.onItemClick(id);
                }
            });

        }

    }
    public interface onRecyclerViewItemClickListener {
        void onItemClick(int id);

    }


}
