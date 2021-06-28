package com.example.userproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.R;
import com.example.userproject.models.Person;


import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private ArrayList<Person> people;
    private onRecyclerViewItemClickListener listener;

    private ArrayList<Person> people(){
        return people;
    }

    public PersonAdapter(ArrayList<Person> people, onRecyclerViewItemClickListener listener) {
        this.people = people;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_sellerman_near_me,null ,
                false);
        PersonViewHolder personViewHolder = new PersonViewHolder(view);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = people.get(position);

        holder.personImage.setImageResource(person.getPersonImage());
        holder.personImage.setTag(person.getPersonId());

    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {

        private ImageView personImage;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            personImage = itemView.findViewById(R.id.customItem_farmer_iv_avatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (int) personImage.getTag();
                    listener.onItemClick(id);
                }
            });

        }
    }    public interface onRecyclerViewItemClickListener {
        void onItemClick(int id);
    }
}
