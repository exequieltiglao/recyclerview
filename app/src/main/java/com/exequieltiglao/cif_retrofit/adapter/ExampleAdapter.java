package com.exequieltiglao.cif_retrofit.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.exequieltiglao.cif_retrofit.R;
import com.exequieltiglao.cif_retrofit.model.ExampleItem;
import com.exequieltiglao.cif_retrofit.model.Post;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private static final String TAG = "ExampleAdapter";

    private ArrayList<Post> postArrayList;

    public ExampleAdapter(ArrayList<Post> mPostArrayList) {
        if (mPostArrayList == null) {
            this.postArrayList = new ArrayList<>();

        } else {
            this.postArrayList = mPostArrayList;
        }

    }

    public void setmPostArrayList(ArrayList<Post> mPostArrayList) {
        this.postArrayList = mPostArrayList;
    }

    static class ExampleViewHolder extends RecyclerView.ViewHolder {

        TextView userId, id, post_title, body;

        ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            userId = itemView.findViewById(R.id.userId);
            id = itemView.findViewById(R.id.id);
            post_title = itemView.findViewById(R.id.post_title);
            body = itemView.findViewById(R.id.body);

        }
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list, parent, false);
        return new ExampleViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int i) {

        Post currentItem = postArrayList.get(i);

        holder.userId.setText("User ID: " + currentItem.getUserId().toString());
        holder.id.setText("ID: " + currentItem.getId().toString());
        holder.post_title.setText("Title: " + currentItem.getTitle());
        holder.body.setText("Body: " + currentItem.getBody());

    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }
}
