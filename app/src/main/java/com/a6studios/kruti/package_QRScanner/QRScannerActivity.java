package com.a6studios.kruti.package_QRScanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a6studios.kruti.R;
import com.a6studios.kruti.package_LanguageSelection.LanguageSelectionActivity;

public class QRScannerActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_reg;
    Button btn_unreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_unreg = (Button) findViewById(R.id.btn_unreg);

        btn_reg.setOnClickListener(this);
        btn_unreg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_unreg){
            finish();
            startActivity(new Intent(this, LanguageSelectionActivity.class));
        }
    }
}
