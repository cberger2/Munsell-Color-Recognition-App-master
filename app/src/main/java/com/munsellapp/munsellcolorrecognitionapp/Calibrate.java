package com.munsellapp.munsellcolorrecognitionapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

public class Calibrate extends AppCompatActivity {
    Bitmap b;
    ImageView caliPic;
    int actualRed, actualGreen, actualBlue;
    int specRed, specGreen, specBlue;
    static int fixRed, fixGreen, fixBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibrate);
        caliPic=(ImageView) findViewById(R.id.caliPic);

        if (getIntent().hasExtra("byteArrayCali")) {
            b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArrayCali"), 0, getIntent().getByteArrayExtra("byteArrayCali").length);

            caliPic.setImageBitmap(b);
        }
        getSpecs();
        fixColors(specRed,specGreen,specBlue);

    }

    public void getSpecs() {
        //When implementing with camera, change field i to get the image taken from the camera,
        //so it's no longer pre loaded in with Android Studio
        ImageView i = new ImageView(this);
        i.setImageBitmap(b);
        int w = i.getWidth();
        int h = i.getHeight();
        Bitmap bitmap = ((BitmapDrawable) i.getDrawable()).getBitmap();
        int pixel = bitmap.getPixel(w, h);
        specRed = Color.red(pixel);
        specBlue = Color.blue(pixel);
        specGreen = Color.green(pixel);

    }

    //MUST PUT IN THE ACTUAL RGB VALUES THAT WE EXPECT TO GET
    public void fixColors(int Red, int Green, int Blue){
        fixRed=actualRed-Red;
        fixGreen=actualGreen-Green;
        fixBlue=actualBlue-Blue;
    }
}
