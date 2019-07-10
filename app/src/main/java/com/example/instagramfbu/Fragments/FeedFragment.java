package com.example.instagramfbu.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagramfbu.Model.Post;
import com.example.instagramfbu.PostAdapter;
import com.example.instagramfbu.R;
import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPosts;
    private SwipeRefreshLayout swipeContainer;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(posts);
        // TODO: link rv
        rvPosts = view.findViewById(R.id.rvPosts);
        rvPosts.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvPosts.setAdapter(postAdapter);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                loadTopPosts();
                postAdapter.clear();
                postAdapter.addAll(posts);
                swipeContainer.setRefreshing(false);
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

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
                        //sb.append("Post[" + i + "] = " + objects.get(i).getDescription() + "\nusername: " + objects.get(i).getUser().getUsername() + "\n\n");
                        posts.add(0, objects.get(i));
                        postAdapter.notifyItemInserted(0);
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

}
