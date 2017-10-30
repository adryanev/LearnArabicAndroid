package com.inkubator.adryan.learnarabic.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.inkubator.adryan.learnarabic.R;

public class HasilActivity extends AppCompatActivity {

    TextView txtScore, txtResult, txtGreetings;
    Button backTo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        intiActionBar();

        txtScore = (TextView)findViewById(R.id.txtScore);
        txtResult = (TextView)findViewById(R.id.txtResult);
        txtGreetings = (TextView)findViewById(R.id.txtGreetings);
        backTo = (Button) findViewById(R.id.back_to_home_button);
        Integer score = getIntent().getExtras().getInt("score",0);
        txtScore.setText("Nilai : " + score);
        blinkText();
        if(score <50)
        {
            txtScore.setTextColor(Color.RED);
            txtResult.setText("Fail");
            txtGreetings.setText("Maaf Nilai anda tidak mencukupi untuk lulus di Ujian ini.");
        }
        else
        {
            txtScore.setTextColor(Color.GREEN);
            txtResult.setText("Pass");
            txtGreetings.setText("Selamat Anda Lulus!!!");
        }
        backTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }
        });
        //Toast.makeText(this,"Score : " + score,Toast.LENGTH_LONG).show();
    }

    private void intiActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Hasil Ujian");
    }

    private void blinkText(){

        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        txtGreetings.setAnimation(anim);
    }

}
