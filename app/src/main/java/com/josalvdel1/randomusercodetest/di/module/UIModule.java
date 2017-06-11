package com.josalvdel1.randomusercodetest.di.module;

import android.app.Application;

import com.josalvdel1.randomusercodetest.util.imageloader.ImageLoader;
import com.josalvdel1.randomusercodetest.util.imageloader.PicassoImageLoader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {

    @Provides
    @Singleton
    public ImageLoader provideImageLoader(Application application) {
        Picasso picasso = Picasso.with(application);
        return new PicassoImageLoader(picasso);
    }
}
