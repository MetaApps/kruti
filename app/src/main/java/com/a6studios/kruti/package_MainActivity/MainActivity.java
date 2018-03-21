package com.a6studios.kruti.package_MainActivity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.a6studios.kruti.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewPager textSlider;
    TextSliderAdapter textSliderAdapter;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSlider = (ViewPager) findViewById(R.id.textSlider);
        linearLayout = (LinearLayout) findViewById(R.id.textSliderLayout);

        textSliderAdapter = new TextSliderAdapter(this);

        textSlider.setAdapter(textSliderAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 4000);
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
