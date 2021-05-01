package com.example.userproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.userproject.R;
import com.example.userproject.models.Farm;
import com.example.userproject.interfaces.onRecyclerViewItemClickListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.FarmViewHolder> {
    ArrayList<Farm> farms;
    onRecyclerViewItemClickListener listener;

    public FarmAdapter(ArrayList<Farm> farms, onRecyclerViewItemClickListener listener) {
        this.farms = farms;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_farmer_product, parent, false);
        FarmViewHolder holder = new FarmViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FarmViewHolder holder, int position) {

        Farm farm = farms.get(position);
        holder.image.setImageResource(farm.getImage());
        holder.prise.setText(farm.getPrise() + "شيكل");
        holder.name.setText(farm.getName());
        holder.Date.setTag(farm.getId());

    }

    @Override
    public int getItemCount() {
        return farms.size();
    }

    class FarmViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView prise;
        private TextView name;
        private TextView Date;

        public FarmViewHolder(@NonNull View itemView) {

            super(itemView);
            image = itemView.findViewById(R.id.farmer_order_iv);
            prise = itemView.findViewById(R.id.farmer_order_price);
            name = itemView.findViewById(R.id.farmer_order_name);
            Date = itemView.findViewById(R.id.farmer_order_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = (int) Date.getTag();
                    listener.onItemClick(id);
                }


            });
        }
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClick(int id);
    }

}

