package com.josalvdel1.randomusercodetest.data.api;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.josalvdel1.randomusercodetest.BuildConfig;
import com.josalvdel1.randomusercodetest.di.qualifiers.Endpoint;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.josalvdel1.randomusercodetest.util.LogUtils.logE;


@Module()
public class ApiModule {

    public static final String DATE_FORMAT_8601 = "yyyy-MM-dd'T'HH:mm:ssZ";

    @Singleton
    @Provides
    public Retrofit provideRetrofit(@Endpoint String endpoint,
                                    GsonConverterFactory gsonConverterFactory,
                                    OkHttpClient okClient) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(endpoint)
                .client(okClient)
                .addConverterFactory(gsonConverterFactory)
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Singleton
    @Provides
    public OkHttpClient provideClient(HttpLoggingInterceptor loggingInterceptor,
                                      Cache cache) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(cache);

        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(loggingInterceptor);
        }
        return okHttpClientBuilder.build();
    }

    @Singleton
    @Provides
    public Cache provideCache(Application context) {
        Cache cache = null;
        try {
            cache = new Cache(new File(context.getCacheDir(), "http"), 10 * 1024 * 1024); //10MB
        } catch (Exception e) {
            logE(getClass().getSimpleName(), e.getMessage());
        }
        return cache;
    }

    @Singleton
    @Provides
    public GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(DATE_FORMAT_8601)
                .create();
    }

    @Singleton
    @Provides
    public UserApiService provideUserApiService(Retrofit retrofit) {
        return retrofit.create(UserApiService.class);
    }
}