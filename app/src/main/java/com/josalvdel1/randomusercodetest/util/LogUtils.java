package com.josalvdel1.randomusercodetest.util;


import android.support.annotation.Nullable;
import android.util.Log;

import com.josalvdel1.randomusercodetest.BuildConfig;

public class LogUtils {
    private static final String LOG_PREFIX = "bipi_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    private LogUtils() {
    }

    private static boolean shouldLog(String tag) {
        return BuildConfig.DEBUG || Log.isLoggable(tag, Log.DEBUG);
    }

    public static String makeLogTag(String str) {
        if (str == null)
            return "Invalid tag";

        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    public static String digestMessage(@Nullable String message) {
        if (message == null) {
            message = "No message provided";
        }

        return message;
    }

    public static void logD(Class clazz, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        String tag = makeLogTag(clazz.getSimpleName());
        if (shouldLog(tag)) {
            Log.d(tag, digestMessage(message));
        }
    }

    public static void logD(String tag, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        tag = makeLogTag(tag);
        if (shouldLog(tag)) {
            Log.d(tag, digestMessage(message));
        }
    }

    public static void logD(String tag, String message, Throwable cause) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        tag = makeLogTag(tag);
        if (shouldLog(tag)) {
            Log.d(tag, digestMessage(message), cause);
        }
    }

    public static void logI(Class clazz, String message) {
        String tag = makeLogTag(clazz.getSimpleName());
        if (shouldLog(tag)) {
            Log.i(tag, digestMessage(message));
        }
    }

    public static void logI(String tag, String message) {
        tag = makeLogTag(tag);
        if (shouldLog(tag)) {
            Log.i(tag, digestMessage(message));
        }
    }

    public static void logI(String tag, String message, Throwable cause) {
        tag = makeLogTag(tag);
        if (shouldLog(tag)) {
            Log.i(tag, digestMessage(message), cause);
        }
    }


    public static void logW(Class clazz, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        String tag = makeLogTag(clazz.getSimpleName());
        if (shouldLog(tag)) {
            Log.w(tag, digestMessage(message));
        }
    }

    public static void logW(String tag, String message) {
        tag = makeLogTag(tag);
        if (shouldLog(tag)) {
            Log.w(tag, digestMessage(message));
        }
    }

    public static void logW(String tag, String message, Throwable cause) {
        tag = makeLogTag(tag);
        if (shouldLog(tag)) {
            Log.w(tag, digestMessage(message), cause);
        }
    }

    public static void logE(Class clazz, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        String tag = makeLogTag(clazz.getSimpleName());
        if (shouldLog(tag)) {
            Log.e(tag, digestMessage(message));
        }
    }

    public static void logE(String tag, String message) {
        tag = makeLogTag(tag);
        if (shouldLog(tag)) {
            Log.e(tag, digestMessage(message));
        }
    }

    public static void logE(String tag, String message, Throwable cause) {
        tag = makeLogTag(tag);
        if (shouldLog(tag)) {
            Log.e(tag, digestMessage(message), cause);
        }
    }


    public static void logWTF(Class clazz, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        String tag = makeLogTag(clazz.getSimpleName());
        if (shouldLog(tag)) {
            Log.wtf(tag, digestMessage(message));
        }
    }

    public static void logWTF(String tag, String message) {
        if (shouldLog(tag)) {
            Log.wtf(tag, digestMessage(message));
        }
    }

    public static void logWTF(String tag, String message, Throwable cause) {
        if (shouldLog(tag)) {
            Log.wtf(tag, digestMessage(message), cause);
        }
    }
}
