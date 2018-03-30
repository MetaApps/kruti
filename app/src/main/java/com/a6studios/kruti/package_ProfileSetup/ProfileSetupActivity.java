package com.a6studios.kruti.package_ProfileSetup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.a6studios.kruti.R;
import com.a6studios.kruti.package_MainActivity.MainActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.BitSet;
import java.util.Locale;
import java.util.jar.Attributes;

public class ProfileSetupActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    Button btn_save_profile;
    ImageView imageView;
    TextView name_tv;

    Uri filePath;

    final int PICK_IMAGE_REQUEST = 71;

    String name;

    TextToSpeech textToSpeech;
    ImageButton btn_product_tts, btn_description_tts, btn_capacity_tts, btn_address_tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        btn_save_profile = (Button) findViewById(R.id.btn_save_profile);
        btn_save_profile.setOnClickListener(this);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        name_tv = (TextView) findViewById(R.id.name_tv);

        /*SharedPreferences sharedPreferences = getSharedPreferences("uid_details",MODE_PRIVATE);
        String name_val = getResources().getString(N);
        String name = sharedPreferences.getString("Name",name_val);*/

        //Text-to-Speech
        //textToSpeech = new TextToSpeech(this,this);
        btn_address_tts = (ImageButton) findViewById(R.id.btn_address_tts);
        btn_address_tts.setOnClickListener(this);
        btn_capacity_tts = (ImageButton) findViewById(R.id.btn_capacity_tts);
        btn_capacity_tts.setOnClickListener(this);
        btn_description_tts = (ImageButton) findViewById(R.id.btn_description_tts);
        btn_description_tts.setOnClickListener(this);
        btn_product_tts = (ImageButton) findViewById(R.id.btn_product_tts);
        btn_product_tts.setOnClickListener(this);

        ScrollView scrollView = (ScrollView)findViewById(R.id.profile_setup_sv);
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });
    }

    void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode != RESULT_CANCELED){
            filePath = data.getData();

            try{
                Bitmap bitMap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setCropToPadding(true);
                imageView.setImageBitmap(bitMap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        textToSpeech = new TextToSpeech(this,this);
        switch (view.getId()){
            case R.id.btn_save_profile : Intent intent = new Intent(this, MainActivity.class);
                                            startActivity(intent);
                                            break;
            case R.id.btn_address_info : speakOut();
            case R.id.btn_capacity_info :
            case R.id.btn_description_info :
            case R.id.btn_product_info : speakOut(); break;
        }

    }

    private void speakOut(View v){

    }

    private void speakOut(){

        //String text = String.valueOf(R.string.select_a_product);
        String text = getResources().getString(R.string.select_a_product);
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    public void onInit(int i) {
        if( i == TextToSpeech.SUCCESS){
            int result = textToSpeech.setLanguage(new Locale("hi"));

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","Language not supported");
            }else{
                speakOut();
            }
        }else{
            Log.e("TTS","Initialization failed");
        }
    }
}
