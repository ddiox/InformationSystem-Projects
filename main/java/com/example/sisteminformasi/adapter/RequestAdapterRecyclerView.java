package com.example.sisteminformasi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sisteminformasi.R;
import com.example.sisteminformasi.admin.InputActivity;
import com.example.sisteminformasi.model.Requests;

import java.util.List;

public class RequestAdapterRecyclerView extends RecyclerView.Adapter<RequestAdapterRecyclerView.MyViewHolder> {
    private List<Requests> moviesList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView tv_title;
        public ImageView img_picture;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.rl_layout);
            tv_title = view.findViewById(R.id.tv_title);
            img_picture = view.findViewById(R.id.img_studio);
        }
    }

    public RequestAdapterRecyclerView(List<Requests> moviesList, Activity activity) {
        this.moviesList = moviesList;
        this.mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Requests movie = moviesList.get(position);

        holder.tv_title.setText(movie.getName());
        Glide.with(mActivity)
                .load(movie.getPicture())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.img_picture);

        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goDetail = new Intent(mActivity, InputActivity.class);
                goDetail.putExtra("id", movie.getKey());
                goDetail.putExtra("name", movie.getName());
                goDetail.putExtra("contact", movie.getContact());
                goDetail.putExtra("address", movie.getAddress());
                goDetail.putExtra("operational", movie.getOperational());
                goDetail.putExtra("equipment", movie.getEquipment());
                goDetail.putExtra("picture", movie.getPicture());

                mActivity.startActivity(goDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
