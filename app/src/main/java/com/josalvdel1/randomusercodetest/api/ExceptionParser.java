package com.josalvdel1.randomusercodetest.api;

import java.lang.annotation.Annotation;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExceptionParser {

    private Retrofit retrofit;
    private GsonConverterFactory converterFactory;

    @Inject
    public ExceptionParser(Retrofit retrofit, GsonConverterFactory converterFactory) {
        this.retrofit = retrofit;
        this.converterFactory = converterFactory;
    }

    public <T> T getResponseAs(ResponseBody body, Class<T> type) throws Exception {
        if (body == null) {
            return null;
        }
        //noinspection unchecked
        Converter<ResponseBody, ?> bodyConverter = converterFactory.responseBodyConverter(type, new Annotation[0], retrofit);
        return (T) bodyConverter.convert(body);
    }
}
