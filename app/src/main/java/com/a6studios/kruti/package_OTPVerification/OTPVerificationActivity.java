package com.a6studios.kruti.package_OTPVerification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.a6studios.kruti.R;
import com.a6studios.kruti.SingletonSharedPref;
import com.a6studios.kruti.package_ProfileSetup.ProfileSetupActivity;

import java.util.Locale;

public class OTPVerificationActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences lang_shared ;

    Button otpVerify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        otpVerify = (Button) findViewById(R.id.btn_verify_otp);
        otpVerify.setOnClickListener(this);

       //setLanguage();

    }

    public void setLanguage(){
        SingletonSharedPref mSSP = SingletonSharedPref.getPreference(this,"KrutiSharedPref");
        String trLanguage = SingletonSharedPref.getLanguage();
        Locale mlocale = new Locale(trLanguage);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = mlocale;
        res.updateConfiguration(conf,dm);
        Intent resume = new Intent(this, OTPVerificationActivity.class);
        startActivity(resume);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, ProfileSetupActivity.class));
    }
}
