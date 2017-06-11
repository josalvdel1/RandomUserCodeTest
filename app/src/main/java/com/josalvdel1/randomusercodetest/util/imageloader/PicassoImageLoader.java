package com.josalvdel1.randomusercodetest.util.imageloader;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PicassoImageLoader implements ImageLoader {

    private Picasso picasso;

    public PicassoImageLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    public void load(String url, ImageView imageView) {
        if (!TextUtils.isEmpty(url) && imageView != null) {
            picasso.load(url).into(imageView);
        }
    }

    @Override
    public void fetch(String url, Callback callback) {
        picasso.load(url).fetch(callback);
    }

    @Override
    public void load(String url, ImageView imageView, Drawable placeholder) {
        if (!TextUtils.isEmpty(url)) {
            picasso.load(url)
                    .fit()
                    .centerInside()
                    .placeholder(placeholder)
                    .into(imageView);
        }
    }

    @Override
    public void loadWithoutEffects(String url, ImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
            picasso.load(url).noFade().noPlaceholder().into(imageView);
        }
    }

    @Override
    public void loadWithCircularTransform(String url, ImageView imageView, Drawable placeholder) {
        if (!TextUtils.isEmpty(url) && imageView != null) {
            picasso.load(url).transform(new CircleTransform()).placeholder(placeholder).into(imageView);
        }
    }

    @Override
    public void loadWithCircularTransform(int resourceId, ImageView imageView, Drawable placeholder) {
        picasso.load(resourceId).transform(new CircleTransform()).placeholder(placeholder).into(imageView);
    }
}