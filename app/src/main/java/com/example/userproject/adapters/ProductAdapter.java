package com.example.userproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.R;
import com.example.userproject.models.Product;
import com.example.userproject.interfaces.onRecyclerViewItemClickListener;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Product> products;
    private onRecyclerViewItemClickListener listener;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ProductAdapter(ArrayList<Product> products,onRecyclerViewItemClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_best_pricely_locationaly, null,
                false);
        ProductViewHolder viewHolder = new ProductViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productImage.setImageResource(product.getProductImage());
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(product.getProductPrice());
        holder.productAddress.setText(product.getProductAddress());

        holder.productImage.setTag(product.getProductId());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productName, productPrice, productAddress;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage_iv);
            productName = itemView.findViewById(R.id.productName_tv);
            productPrice = itemView.findViewById(R.id.productPrice_tv);
            productAddress = itemView.findViewById(R.id.productAddress_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (int) productImage.getTag();
                    listener.onItemClick(id);
                }
            });

        }
    }
    public interface onRecyclerViewItemClickListener {
        void onItemClick(int id);
    }
}
