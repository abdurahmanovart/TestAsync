package com.example.arturx.testasync;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "https://wallpapercave.com/wp/GoYYfQj.jpg";

    private ImageView mImageView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.pic_image_view);
        new MyPicasso.Builder()
                .url(URL)
                .toImageView(mImageView)
                .build();

    }

}