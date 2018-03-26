package com.a6studios.kruti.package_MainActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a6studios.kruti.R;
import com.a6studios.kruti.package_AskQuery.AskQueryActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager textSlider;
    TextSliderAdapter textSliderAdapter;
    LinearLayout textSlider_ll;
    LinearLayout ask_query_ll, chat, profile, call;
    TextView ask_query_text, chat_text, profile_text, call_text;
    CardView ask_query_cv, call_cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ask_query
        ask_query_ll = (LinearLayout) findViewById(R.id.ask_query_ll);
        ask_query_text = (TextView) findViewById(R.id.ask_query_text);
        ask_query_cv = (CardView) findViewById(R.id.ask_query_cv);

        chat = (LinearLayout) findViewById(R.id.chat);
        profile = (LinearLayout) findViewById(R.id.profile);

        //call
        call = (LinearLayout) findViewById(R.id.call);
        call_text = (TextView) findViewById(R.id.call_text);
        call_cv = (CardView) findViewById(R.id.call_cv);
        call.setOnClickListener(this);
        call_cv.setOnClickListener(this);
        call_text.setOnClickListener(this);

        //ask_query
        ask_query_ll.setOnClickListener(this);
        ask_query_text.setOnClickListener(this);
        ask_query_cv.setOnClickListener(this);


        chat.setOnClickListener(this);
        profile.setOnClickListener(this);


        //textSlider
        textSlider = (ViewPager) findViewById(R.id.textSlider);
        textSlider_ll = (LinearLayout) findViewById(R.id.textSliderLayout);
        textSliderAdapter = new TextSliderAdapter(this);
        textSlider.setAdapter(textSliderAdapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 4000);
    }

    @Override
    public void onClick(View view) {
        if (view == ask_query_ll || view == ask_query_cv || view == ask_query_text) {
            Intent intent = new Intent(this, AskQueryActivity.class);
            startActivity(intent);
        }

        if (view == call || view == call_cv || view == call_text) {

            String phnum = "1234567890";
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phnum));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return;
            }
            startActivity(callIntent);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }

    public class SliderTimer extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int i = textSliderAdapter.getCount();
                    int j = textSlider.getCurrentItem();
                    textSlider_ll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    //count.setText("number : "+i+"\ncurrent : "+j);
                    if(j+1 == i){
                        textSlider.setCurrentItem(0);
                    }else{
                        textSlider.setCurrentItem(j+1);
                    }
                }
            });
        }
    }
}
