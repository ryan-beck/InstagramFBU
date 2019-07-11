package com.example.instagramfbu.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instagramfbu.Model.Post;
import com.example.instagramfbu.R;
import com.parse.ParseException;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    ImageView ivPicture;
    TextView tvUsername;
    TextView tvDescription;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivPicture = findViewById(R.id.ivPicture);
        tvUsername = findViewById(R.id.tvUsername);
        tvDescription = findViewById(R.id.tvDescription);
        tvDate = findViewById(R.id.tvDate);

        Intent intent = getIntent();
        Post post = Parcels.unwrap(intent.getParcelableExtra(Post.class.getSimpleName()));
        tvUsername.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        Date date = post.getCreatedAt();
        tvDate.setText(getRelativeTimeAgo(date));
        Bitmap image = null;
        try {
            image = BitmapFactory.decodeFile(post.getImage().getFile().getAbsolutePath());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // RESIZE BITMAP, see section below
        // Load the taken image into a preview
        ivPicture.setImageBitmap(image);

    }

    public String getRelativeTimeAgo(Date date) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate;
        long dateMillis = date.getTime();
        relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        return relativeDate;
    }
}
