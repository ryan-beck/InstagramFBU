package com.example.instagramfbu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instagramfbu.Model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    public List<Post> mPosts;
    static Context context;

    public PostAdapter(List<Post> posts) {
        mPosts = posts;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.layout_post, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder viewHolder, int i) {
        Post post = mPosts.get(i);

        viewHolder.tvUsername.setText(post.getUser().getUsername());
        //viewHolder.tvTimestamp.setText(post);
        viewHolder.tvDescription.setText(post.getDescription());
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPicture;
        TextView tvUsername;
        TextView tvTimestamp;
        TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPicture = itemView.findViewById(R.id.ivPreview);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvTimestamp = itemView.findViewById(R.id.tvTimeStamp);
            tvDescription = itemView.findViewById(R.id.tvDescrition);
        }
    }
}
