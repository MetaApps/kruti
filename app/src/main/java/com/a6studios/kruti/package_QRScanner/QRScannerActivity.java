package com.a6studios.kruti.package_QRScanner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a6studios.kruti.QRXMLParser;
import com.a6studios.kruti.R;
import com.a6studios.kruti.package_LanguageSelection.LanguageSelectionActivity;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class QRScannerActivity extends AppCompatActivity implements View.OnClickListener {

    SurfaceView cameraView;
    TextView barcodeInfo;
    private static final int REQUEST_CAMERA_PERMISSION = 201;

    Button btn_reg;
    Button btn_unreg;

    String name, vtc, dist, pin, uid__details, uid;
    String uidia_xml;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        barcodeInfo = (TextView) findViewById(R.id.code_info);

        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_unreg = (Button) findViewById(R.id.btn_unreg);

        btn_reg.setOnClickListener(this);
        btn_unreg.setOnClickListener(this);

        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        final CameraSource cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(640,480).build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            //@SuppressLint("MissingPermission")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try{
                    if (ActivityCompat.checkSelfPermission(QRScannerActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                        cameraSource.start(cameraView.getHolder());
                        Toast.makeText(QRScannerActivity.this,"QR scan", Toast.LENGTH_LONG).show();
                    }
                    else{
                        ActivityCompat.requestPermissions(QRScannerActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                        surfaceCreated(cameraView.getHolder());
                    }
                }catch (IOException ie){
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if(barcodes.size()!=0){
                    barcodeInfo.post(new Runnable() {
                        @Override
                        public void run() {
                            //barcodeInfo.setText(barcodes.valueAt(0).rawValue);
                            uidia_xml = barcodes.valueAt(0).rawValue;
                            QRXMLParser qrxmlParser = new QRXMLParser(uidia_xml);
                            name = qrxmlParser.getAttribute("name");
                            uid = qrxmlParser.getAttribute("uid");
                            dist = qrxmlParser.getAttribute("dist");
                            vtc = qrxmlParser.getAttribute("vtc");
                            pin = qrxmlParser.getAttribute("pc");
                            uid__details = name+","+uid+","+dist+","+vtc+","+pin;
                            sharedPreferences = getSharedPreferences("uid_details",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Name",name);
                            editor.putString("UID",uid);
                            editor.putString("District",dist);
                            editor.putString("VTC",vtc);
                            editor.putString("PINCODE",pin);
                            editor.putString("UIDIA_XML",uidia_xml);
                            editor.apply();
                            editor.commit();
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view == btn_unreg){
            Intent languageSelectionActivity = new Intent(this,LanguageSelectionActivity.class);
            finish();
            startActivity(languageSelectionActivity);
        }
    }
}
