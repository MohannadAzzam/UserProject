package com.example.userproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.userproject.R;
import com.example.userproject.models.Category;
import com.example.userproject.interfaces.onRecyclerViewItemClickListener;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    private ArrayList<Category> categories;
    private onRecyclerViewItemClickListener listener;

    //  private  boolean isOpen = false;
    public CategoryAdapter(Context context, ArrayList<Category> categories, onRecyclerViewItemClickListener listener) {
        this.context = context;
        this.categories = categories;
        this.listener = listener;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_item_category, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.categoreimg.setImageResource(category.getImg());
        holder.textName.setText(category.getName());
//        Glide.with(context)
//                .load(categories.get(position).getImg())
//                .into(holder.categoreimg);

        holder.categoreimg.setTag(category.getImg());

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView categoreimg;
        TextView textName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoreimg = itemView.findViewById(R.id.category_image);
            textName = itemView.findViewById(R.id.category_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = (int) categoreimg.getTag();
                    listener.onItemClick(id);
                }
            });
        }
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClick(int id);
    }
}
