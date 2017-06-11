package com.josalvdel1.randomusercodetest.util;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

public class PreferencesUtils {

    private SharedPreferences mSharedPreferences;

    @Inject
    public PreferencesUtils(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    /**
     * Storage the pair key-object in preference
     */
    public void setPreferences(@NonNull String key,
                               Object object) {

        SharedPreferences.Editor ed = mSharedPreferences.edit();
        if (object instanceof String) {
            ed.putString(key, (String) object);
        } else if (object instanceof Boolean) {
            ed.putBoolean(key, (Boolean) object);
        } else if (object instanceof Integer) {
            ed.putInt(key, (Integer) object);
        } else if (object instanceof Long) {
            ed.putLong(key, (Long) object);
        } else if (object instanceof Float) {
            ed.putFloat(key, (Float) object);
        } else {
            String json = null;
            if (object != null) {
                json = gsonBuilder().create().toJson(object);
            }
            ed.putString(key, json);
        }
        ed.apply();
    }

    // Get the stored object associate with the key string
    @SuppressWarnings("rawtypes")
    public <T> T getPreferences(String key,
                                Class<T> clazz, T defaultValue) {

        if (key != null && clazz != null) {

            try {
                if (clazz.equals(String.class)) {
                    return clazz.cast(mSharedPreferences.getString(key, ((String) defaultValue)));
                } else if (clazz.equals(Boolean.class)) {
                    return clazz.cast(mSharedPreferences.getBoolean(key, ((Boolean) defaultValue)));
                } else if (clazz.equals(Integer.class)) {
                    return clazz.cast(mSharedPreferences.getInt(key, ((Integer) defaultValue)));
                } else if (clazz.equals(Long.class)) {
                    return clazz.cast(mSharedPreferences.getLong(key, ((Long) defaultValue)));
                } else if (clazz.equals(Float.class)) {
                    return clazz.cast(mSharedPreferences.getFloat(key, ((Float) defaultValue)));
                } else {
                    String json = mSharedPreferences.getString(key, ((String) defaultValue));
                    return clazz.cast(
                            gsonBuilder().create().fromJson(json, clazz));
                }
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "getPreferences exception" + e.getMessage());
            }
        }
        return null;
    }

    /**
     * Remove stored object that it is associated with key param
     */
    public void removePreference(String key) {
        setPreferences(key, null);
    }

    public GsonBuilder gsonBuilder() {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, arg1, arg2) -> {

            if (json.getAsString() != null && "".compareTo(json.getAsString()) != 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(Long.parseLong(json.getAsString()));
                return calendar.getTime();
            } else {
                return null;
            }
        });

        builder.registerTypeAdapter(Date.class, (JsonSerializer<Date>) (arg0, arg1, arg2)
                -> new JsonPrimitive(arg0.getTime()));

        return builder;
    }
}