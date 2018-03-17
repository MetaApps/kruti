package com.a6studios.kruti.package_OTPVerification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a6studios.kruti.R;
import com.a6studios.kruti.package_ProfileSetup.ProfileSetupActivity;

public class OTPVerificationActivity extends AppCompatActivity implements View.OnClickListener {

    Button otpVerify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        otpVerify = (Button) findViewById(R.id.btn_verify_otp);
        otpVerify.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, ProfileSetupActivity.class));
    }
}
