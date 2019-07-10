package com.example.instagramfbu.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagramfbu.Model.Post;
import com.example.instagramfbu.PostAdapter;
import com.example.instagramfbu.R;
import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.List;

public class FeedFragment extends Fragment {

    PostAdapter postAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loadTopPosts();

    }

    private void loadTopPosts() {
        final Post.Querey postsQuerey = new Post.Querey();
        postsQuerey.getTop().withUser();

        postsQuerey.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                StringBuilder sb = new StringBuilder();
                if(e == null) {
                    for(int i = 0; i < objects.size(); i++) {
                        sb.append("Post[" + i + "] = " + objects.get(i).getDescription() + "\nusername: " + objects.get(i).getUser().getUsername() + "\n\n");
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });
    }
}
