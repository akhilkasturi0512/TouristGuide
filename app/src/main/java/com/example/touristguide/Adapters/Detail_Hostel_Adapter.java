package com.example.touristguide.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.touristguide.ApiCall.Models.PropertyDetailResBean;
import com.example.touristguide.R;
import com.example.touristguide.Utils.ApiConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Detail_Hostel_Adapter extends RecyclerView.Adapter<Detail_Hostel_Adapter.MyViewHolder> {

    List<PropertyDetailResBean.Data.PropertyImagesItem>list;
     private Context context;
     private ItemClickListner listner;

    public interface ItemClickListner{
        public void OnItemClicked(int position);
    }

    public Detail_Hostel_Adapter(List<PropertyDetailResBean.Data.PropertyImagesItem> list, Context context, ItemClickListner listner) {
        this.list = list;
        this.context = context;
        this.listner = listner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_detail_child_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get().load(ApiConstants.BASE_IMAGE_URL+list.get(position).getImage()).into(holder.imgHostel);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHostel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHostel = itemView.findViewById(R.id.img_Hostel);
        }
    }
}
