package com.a6studios.kruti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.a6studios.kruti.package_QRScanner.QRScannerActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash_screen);

        startActivity(new Intent(this, QRScannerActivity.class));

        finish();
    }
}
