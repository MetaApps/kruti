package com.a6studios.kruti.package_MainActivity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a6studios.kruti.R;

/**
 * Created by SASANKA on 1/15/2018.
 */

public class TextSliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private String[] texts = {"Handloom and Handicraft Emporiums from Gujarat, Kerala, Bengal, Andhra Pradesh, Tamil Nadu, Karnataka, Uttaranchal and Madhya Pradesh, and products of TRIFED and branded items of cooperative sectors will be available at the Mela.",
            "TRIFED in association with tribal artisans also participates in exhibitions / craft melas organized by other Agencies of the Central/State Governments throughout the country with an objective to provide opportunity to tribal artisans to showcase and market their handicraft products.",
            " NZCC has invited acclaimeded handloom/ handicraft artisans selected by Tribal Cooperative Marketing Development Federation of India Limited (TRIFED) and Union ministry of tribal affairs. He said the fair's objective was to showcase Indian handicrafts, handlooms, and folk traditions and dances.",
            "Insights Daily Current Events, 20 November 2014 Annual Tribal Art and Crafts Mela The annual National Tribal Crafts Mela popularly known as 'Aadishilp' will begin here tomorrow. Organized by:Tribal Cooperative Marketing Development Federation of India Ltd. (TRIFED). "};


    public TextSliderAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {

        return texts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, null);

        TextView textView = (TextView) view.findViewById(R.id.sliderTextView);
        textView.setText(texts[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView((view));
    }
}
