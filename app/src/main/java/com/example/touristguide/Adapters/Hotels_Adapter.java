package com.example.touristguide.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.touristguide.ApiCall.Models.HotelsPropertyResBean;
import com.example.touristguide.R;
import com.example.touristguide.Utils.ApiConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Hotels_Adapter extends RecyclerView.Adapter<Hotels_Adapter.MyViewHolder>{

    ArrayList<HotelsPropertyResBean.DataItem> list;
    Context context;
    ItemClickListner listner;

    public interface ItemClickListner{
        public void OnItemClicked(int position);
    }

    public Hotels_Adapter(ArrayList<HotelsPropertyResBean.DataItem> list, Context context, ItemClickListner listner) {
        this.list = list;
        this.context = context;
        this.listner = listner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_child_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,@SuppressLint("RecyclerView")  final int position) {

        holder.txtHostelname.setText(list.get(position).getBusinessName());
        holder.txtLocation.setText(list.get(position).getAddress());
        Picasso.get().load(ApiConstants.BASE_IMAGE_URL+list.get(position).getImage()).into(holder.imgHostel);

        holder.cvRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               listner.OnItemClicked(position);

            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHostel;
        TextView txtHostelname,txtLocation;
        CardView cvRoot;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHostel= itemView.findViewById(R.id.img_Hostel);
            txtHostelname = itemView.findViewById(R.id.txt_Hostel_Name);
            txtLocation = itemView.findViewById(R.id.txt_Location);
            cvRoot = itemView.findViewById(R.id.cv_Root);

        }
    }
}
