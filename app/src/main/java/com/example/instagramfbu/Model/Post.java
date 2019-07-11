package com.example.instagramfbu.Model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


@ParseClassName("Post")
public class Post extends ParseObject {
    private static final String KEY_DESCRIPTION = "Description";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_USER = "user";

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String s) {
        put(KEY_DESCRIPTION, s);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile file) {
        put(KEY_IMAGE, file);
    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }


    public static class Querey extends ParseQuery<Post> {
        public Querey() {
            super(Post.class);
        }

        public Querey getTop() {
            setLimit(20);
            return this;
        }

        public Querey withUser() {
            include("user");
            return this;
        }
    }
}
