package com.example.touristguide.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.touristguide.ApiCall.Models.SearchResBean;
import com.example.touristguide.R;

import java.util.ArrayList;

public class SearchItemListApdapter extends BaseAdapter {
    private ArrayList<SearchResBean.DataItem> list;
    LayoutInflater inflater;
    ItemClickListner listner;

    public interface ItemClickListner{
        void onItemClick(int position);
    }

    public SearchItemListApdapter(Context context,ArrayList<SearchResBean.DataItem> list, ItemClickListner listner) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.listner = listner;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        LinearLayout Container;
        TextView CategoryItemName;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.child_seach_layout,null);
            holder.CategoryItemName =view.findViewById(R.id.categoryItemName);
            holder.Container = view.findViewById(R.id.Container);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.CategoryItemName.setText(list.get(i).getBusinessName());
        holder.Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listner.onItemClick(i);
            }
        });

        return view;
    }
}
