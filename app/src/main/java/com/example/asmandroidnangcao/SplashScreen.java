package com.example.asmandroidnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;
    TextView ten,loimoi;
    LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        animationView=findViewById(R.id.aniamtion);
        imageView=findViewById(R.id.logo);
        ten=findViewById(R.id.ten);
        loimoi=findViewById(R.id.loimoi);
        animationView.animate().translationY(1600).setDuration(1000).setStartDelay(4000);
        loimoi.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        ten.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        imageView.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
//                Pair[] pairs = new Pair[2];
//                pairs[0]= new Pair<View,String>(logo,"logo_transition");
//                pairs[1]= new Pair<View,String>(ten,"ten_transition");

                    startActivity(i);

                }


        },4780);

    }

    }

