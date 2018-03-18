package com.a6studios.kruti.package_LanguageSelection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.a6studios.kruti.R;
import com.a6studios.kruti.SharedPref;
import com.a6studios.kruti.package_OTPVerification.OTPVerificationActivity;

import java.sql.Array;
import java.util.Locale;

public class LanguageSelectionActivity extends AppCompatActivity {

    /*SharedPreferences lang_shared ;
    SharedPreferences.Editor editor;*/

    Locale mylocale;
    String lang;
    String[] languages = {"हिन्दी",
            "বাংলা	",
            "తెలుగు",
            "मराठी	",
            "தமிழ்",
            "ગુજરાતી",
            "ಕನ್ನಡ",
            "മലയാളം",
            "ਪੰਜਾਬੀ	",
            "English"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);

        ListView languageList = (ListView)findViewById(R.id.lang_list);

        //String[] languages = getResources().getStringArray(R.array.language_list);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.language_list_label, languages);
        languageList.setAdapter(arrayAdapter);

        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*lang_shared = getSharedPreferences("kruti", MODE_PRIVATE);
                editor=lang_shared.edit();*/

                //Intent intent = new Intent(this, OTPVerificationActivity.class);

                switch (position){
                    case 0:saveLanguageDetails("hi");startOTPVerificationActivity();break;
                    case 1:saveLanguageDetails("bn");startOTPVerificationActivity();break;
                    case 2:saveLanguageDetails("te");startOTPVerificationActivity();break;
                    case 3:saveLanguageDetails("mr");startOTPVerificationActivity();break;
                    case 4:saveLanguageDetails("tn");startOTPVerificationActivity();break;
                    case 5:saveLanguageDetails("gu");startOTPVerificationActivity();break;
                    case 6:saveLanguageDetails("kn");startOTPVerificationActivity();break;
                    case 7:saveLanguageDetails("ml");startOTPVerificationActivity();break;
                    case 8:saveLanguageDetails("pa");startOTPVerificationActivity();break;
                    case 9:saveLanguageDetails("en");startOTPVerificationActivity();
                }

                /*editor.putString("lang",lang);
                editor.commit();*/

            }
        });


    }

    public void startOTPVerificationActivity(){
        Intent intent = new Intent(this, OTPVerificationActivity.class);
        startActivity(intent);
    }

    public void saveLanguageDetails(String lang){
        new SharedPref(this).saveLanguage(lang);
    }

    public void setLocale(String lang){
        mylocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = mylocale;
        res.updateConfiguration(conf,dm);
        startActivity(new Intent(this, OTPVerificationActivity.class));
    }
}
