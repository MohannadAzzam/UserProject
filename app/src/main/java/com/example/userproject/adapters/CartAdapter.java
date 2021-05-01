package com.example.userproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.R;
import com.example.userproject.models.Cart;
import com.example.userproject.interfaces.onRecyclerViewItemClickListener;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<Cart> carts;
    private onRecyclerViewItemClickListener listener;


    public CartAdapter(ArrayList<Cart> carts, onRecyclerViewItemClickListener listener) {
        this.carts = carts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart_items,null,false);
        CartViewHolder cartViewHolder = new CartViewHolder(view);
            return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = carts.get(position);

        holder.orderImageView.setImageResource(cart.getProductImage());
        holder.orderName.setText(cart.getProductName());
        holder.orderPrice.setText(cart.getProductPrice()+" شيكل ");
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        private ImageView orderImageView;
        private TextView orderPrice, orderName;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            orderImageView = itemView.findViewById(R.id.cart_order_iv);
            orderPrice = itemView.findViewById(R.id.cart_order_tv_price);
            orderName = itemView.findViewById(R.id.cart_order_tv_name);

        }
    }
}
