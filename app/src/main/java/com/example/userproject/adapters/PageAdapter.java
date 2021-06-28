package com.example.userproject.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.userproject.models.Item_v;

import java.util.ArrayList;

public class PageAdapter extends FragmentStatePagerAdapter {

    ArrayList<Item_v> item = new ArrayList<>();


    public PageAdapter(@NonNull  FragmentManager fm) {
        super(fm);
    }

    public void addTab(Item_v tab){
        item.add(tab);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return item.get(position).getFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return item.get(position).getTab_name();
    }

    @Override
    public int getCount() {
        return item.size();
    }
}
