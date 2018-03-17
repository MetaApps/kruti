package com.a6studios.kruti.package_LanguageSelection;

import android.content.Intent;
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
import com.a6studios.kruti.package_OTPVerification.OTPVerificationActivity;

import java.sql.Array;
import java.util.Locale;

public class LanguageSelectionActivity extends AppCompatActivity {

    Locale mylocale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);

        ListView languageList = (ListView)findViewById(R.id.lang_list);

        String[] languages = getResources().getStringArray(R.array.language_list);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.language_list_label);
        languageList.setAdapter(arrayAdapter);

        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:setLocale("hi");
                    case 1:setLocale("bn");
                    case 2:setLocale("te");
                    case 3:setLocale("mr");
                    case 4:setLocale("tn");
                    case 5:setLocale("gu");
                    case 6:setLocale("kn");
                    case 7:setLocale("ml");
                    case 8:setLocale("pa");
                    case 9:setLocale("en");
                }
            }
        });
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
