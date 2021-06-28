package com.example.userproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.R;
import com.example.userproject.models.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    ArrayList<Order> orders  ;
    onRecyclerViewItemClickListener  listener;


    public OrderAdapter(ArrayList<Order> orders, onRecyclerViewItemClickListener listener) {
        this.orders = orders;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_custom_design,viewGroup,false);
        OrderViewHolder holder = new OrderViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.image.setImageResource(order.getImage());
        holder.prise.setText(order.getPrise()+"شيكل");
        holder.Order_status.setText(order.getOrder_status());

        holder.Date.setTag(order.getId());

    }

    @Override
    public int getItemCount() {
        return orders.size();

    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView prise;
        private TextView Order_status;
        private TextView Date;
        private  View View;
        public OrderViewHolder(@NonNull View itemView) {

            super(itemView);

            image = itemView.findViewById(R.id.order_id_imageview);
            prise = itemView.findViewById(R.id.order_id_textview_pricequantity);
            Order_status = itemView.findViewById(R.id.order_id_textview_orderstatus);
            Date = itemView.findViewById(R.id.order_id_textview_orderdate);
            View = itemView.findViewById(R.id.order_id_linearvertical);

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




