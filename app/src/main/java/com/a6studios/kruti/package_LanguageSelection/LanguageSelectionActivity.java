package com.a6studios.kruti.package_LanguageSelection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.a6studios.kruti.FirestoreDataBase;
import com.a6studios.kruti.R;
import com.a6studios.kruti.SingletonSharedPref;
import com.a6studios.kruti.package_ProfileSetup.ProfileSetupActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;

import java.sql.Array;
import java.util.Arrays;
import java.util.Locale;

public class LanguageSelectionActivity extends AppCompatActivity {

    /*SharedPreferences lang_shared ;
    SharedPreferences.Editor editor;*/

    Locale mylocale;
    String lang;
    private static final int RC_SIGN_IN = 123;
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
                    case 0:lang = "hi";break;
                    case 1:lang = "bn";break;
                    case 2:lang = "te";break;
                    case 3:lang = "mr";break;
                    case 4:lang = "ta";break;
                    case 5:lang = "gu";break;
                    case 6:lang = "kn";break;
                    case 7:lang = "ml";break;
                    case 8:lang = "pa";break;
                    case 9:lang = "en";
                }

                setLocale(lang);
                /*saveLanguageDetails(lang);
                startOTPVerificationActivity();*/

                /*editor.putString("lang",lang);
                editor.commit();*/

            }
        });


    }

    public void startOTPVerificationActivity(){
        /*Intent intent = new Intent(this, OTPVerificationActivity.class);
        startActivity(intent);*/
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Arrays.asList(
                                        new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()
                                ))
                        .build(),
                RC_SIGN_IN);
    }



    public void saveLanguageDetails(String lang){
        SingletonSharedPref mSSP = SingletonSharedPref.getPreference(this,"KrutiSharedPref");
        mSSP.putLanguage(lang);
    }

    public void setLocale(String lang){
        mylocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = mylocale;
        res.updateConfiguration(conf,dm);
        startOTPVerificationActivity();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            // Successfully signed in
            if (resultCode == ResultCodes.OK) {
                FirestoreDataBase fb = FirestoreDataBase.getFirestoreDatabase();
                Intent i = new Intent(this, ProfileSetupActivity.class);
                startActivity(i);
                finish();
                return;
            } else {
                // Sign in failed
                String s;
                if (response == null) {
                    // User pressed back button
                    Log.e("Login","Login canceled by User");
                    return;
                }
                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Log.e("Login","No Internet Connection");
                    return;
                }
                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Log.e("Login","Unknown Error");
                    return;
                }
            }
            Log.e("Login","Unknown sign in response");
        }
    }
}
