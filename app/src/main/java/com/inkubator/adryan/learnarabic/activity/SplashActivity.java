package com.inkubator.adryan.learnarabic.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.inkubator.adryan.learnarabic.R;
import com.squareup.picasso.Picasso;

public class SplashActivity extends AppCompatActivity {

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        iv = (ImageView) findViewById(R.id.gambarUin);

        Picasso.with(getApplicationContext()).load(R.drawable.logo_uin).resize(200,200).into(iv);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },3000);
    }
}
