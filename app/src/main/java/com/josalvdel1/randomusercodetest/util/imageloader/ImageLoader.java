package com.josalvdel1.randomusercodetest.util.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Callback;

public interface ImageLoader {
    void load(String url, ImageView imageView);

    void fetch(String url, Callback callback);

    void load(String url, ImageView imageView, Drawable placeholder);

    void loadWithoutEffects(String url, ImageView imageView);

    void loadWithCircularTransform(String url, ImageView imageView, Drawable placeholder);

    void loadWithCircularTransform(int resourceId, ImageView imageView, Drawable placeholder);
}
