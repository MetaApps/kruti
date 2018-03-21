package com.a6studios.kruti.package_MainActivity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a6studios.kruti.R;
import com.a6studios.kruti.package_AskQuery.AskQueryActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager textSlider;
    TextSliderAdapter textSliderAdapter;
    LinearLayout linearLayout;
    LinearLayout ask_query_ll, chat, profile, call;
    TextView ask_query_text, chat_text, profile_text, call_text;
    CardView ask_query_cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSlider = (ViewPager) findViewById(R.id.textSlider);
        linearLayout = (LinearLayout) findViewById(R.id.textSliderLayout);

        //ask_query
        ask_query_ll = (LinearLayout) findViewById(R.id.ask_query_ll);
        ask_query_text = (TextView) findViewById(R.id.ask_query_text);
        ask_query_cv = (CardView) findViewById(R.id.ask_query_cv);

        chat = (LinearLayout) findViewById(R.id.chat);
        profile = (LinearLayout) findViewById(R.id.profile);
        call = (LinearLayout) findViewById(R.id.call);

        //ask_query
        ask_query_ll.setOnClickListener(this);
        ask_query_text.setOnClickListener(this);
        ask_query_cv.setOnClickListener(this);


        chat.setOnClickListener(this);
        profile.setOnClickListener(this);
        call.setOnClickListener(this);

        textSliderAdapter = new TextSliderAdapter(this);

        textSlider.setAdapter(textSliderAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 4000);
    }

    @Override
    public void onClick(View view) {
        if(view == ask_query_ll || view == ask_query_cv || view ==ask_query_text ){
            Intent intent = new Intent(this, AskQueryActivity.class);
            startActivity(intent);
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
                    linearLayout.setOnClickListener(new View.OnClickListener() {
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
