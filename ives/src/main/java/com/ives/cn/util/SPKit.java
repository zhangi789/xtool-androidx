package com.ives.cn.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author 张海洋
 * @Date on 2019/05/14.
 * @org 上海..科技有限公司
 * @describe  SharedPreferences
 */
public class SPKit {


    public static SharedPreferences mSp;
    public static String SP_SPLASH = "config_splash";

    public static void putString(Context context, String key, String value) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_SPLASH, Context.MODE_PRIVATE);
        }

        mSp.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defValue) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_SPLASH, Context.MODE_PRIVATE);
        }
        return mSp.getString(key, defValue);
    }


    public static void putInt(Context context, String key, int value) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_SPLASH, Context.MODE_PRIVATE);
        }

        mSp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_SPLASH, Context.MODE_PRIVATE);
        }
        return mSp.getInt(key, defValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_SPLASH, Context.MODE_PRIVATE);
        }

        mSp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_SPLASH, Context.MODE_PRIVATE);
        }
        return mSp.getBoolean(key, defValue);
    }


    public static void putFloat(Context context, String key, float value) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_SPLASH, Context.MODE_PRIVATE);
        }

        mSp.edit().putFloat(key, value).commit();
    }

    public static float getFloat(Context context, String key, float defValue) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_SPLASH, Context.MODE_PRIVATE);
        }
        return mSp.getFloat(key, defValue);
    }


}
