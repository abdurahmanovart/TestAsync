package com.example.arturx.testasync;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by arturx on 02.12.17.
 */

public class MyPicasso {

    private String mUrl;

    private ImageView mImageView;

    private MyPicasso() {
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public static class Builder {
        private MyPicasso mMyPicasso;
        private Future mFuture;

        public Builder() {
            mMyPicasso = new MyPicasso();
        }

        public Builder url(String url) {
            mMyPicasso.setUrl(url);
            makeFuture();
            return this;
        }

        public Builder toImageView(ImageView imageView) {
            try {
                Bitmap bitmap = (Bitmap) mFuture.get();
                imageView.setImageBitmap(bitmap);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return this;
        }

        public MyPicasso build() {
            return mMyPicasso;
        }

        private void makeFuture() {
            mFuture = Executors.newSingleThreadExecutor().submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    InputStream stream = new URL(mMyPicasso.getUrl()).openStream();
                    return BitmapFactory.decodeStream(stream);
                }
            });
        }
    }
}
