package com.barisalgul.instravel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barisalgul.instravel.R;
import com.barisalgul.instravel.model.PostModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder> {
    private ArrayList<PostModel> postModelArrayList;

    public FeedRecyclerAdapter(ArrayList<PostModel> postModelArrayList) {
        this.postModelArrayList = postModelArrayList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_post, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.userEMailText.setText(postModelArrayList.get(position).getUserEMail());
        holder.commentText.setText(postModelArrayList.get(position).getComment());
        Picasso.get().load(postModelArrayList.get(position).getDownloadUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return postModelArrayList.size();
    }

    static class PostHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView userEMailText, commentText;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_item_photo);
            userEMailText = itemView.findViewById(R.id.tv_item_useremail);
            commentText = itemView.findViewById(R.id.tv_item_comment);
        }
    }
}
