package com.a6studios.kruti;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 3/18/2018.
 */

public class SharedPref {
    private Context context;

    public SharedPref(Context context){
        this.context = context;
    }

    public void saveLanguage(String lang){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SelectedLanguage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("language", lang);
        editor.apply();
    }

    public String getLanguage() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SelectedLanguage", Context.MODE_PRIVATE);
        return sharedPreferences.getString("language", "");
    }
}
