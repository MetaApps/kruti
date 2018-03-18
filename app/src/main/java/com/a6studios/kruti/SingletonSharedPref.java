package com.a6studios.kruti;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 3/18/2018.
 */

public class SingletonSharedPref {
    private static SingletonSharedPref mSinletonSharedPref;
    private static SharedPreferences mSharedPreferences;
    //private Context context;

    /*public SharedPref(Context context){
        this.context = context;
    }*/

    SingletonSharedPref(Context context, String prefFileName)
    {
        mSharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    public static SingletonSharedPref getPreference(Context context, String prefFileName)
    {
        if(mSinletonSharedPref == null) {

            mSinletonSharedPref=new SingletonSharedPref(context,prefFileName);
        }
        return mSinletonSharedPref;
    }

    public static void putLanguage(String lang){
        //sharedPreferences = context.getSharedPreferences("SelectedLanguage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("language", lang);
        editor.apply();
    }

    public static String getLanguage() {
        //SharedPreferences sharedPreferences = context.getSharedPreferences("SelectedLanguage", Context.MODE_PRIVATE);
        return mSharedPreferences.getString("language", "");
    }
}
