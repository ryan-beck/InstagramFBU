package com.example.instagramfbu;

import android.app.Application;

import com.example.instagramfbu.Model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("ryan-beck")
                .clientKey("ladHou17ladBos18")
                .server("http://ryan-beck-fbu-instagram.herokuapp.com/parse")
                .build();
        Parse.initialize(configuration);
    }
}
