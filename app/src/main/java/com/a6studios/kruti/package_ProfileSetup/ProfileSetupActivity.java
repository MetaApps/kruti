package com.a6studios.kruti.package_ProfileSetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a6studios.kruti.R;
import com.a6studios.kruti.package_MainActivity.MainActivity;

public class ProfileSetupActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_save_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        btn_save_profile = (Button) findViewById(R.id.btn_save_profile);
        btn_save_profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
