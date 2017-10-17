package com.inkubator.adryan.learnarabic.activity;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.inkubator.adryan.learnarabic.R;

public class HasilActivity extends AppCompatActivity {

    TextView txtScore, txtResult, txtGreetings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        intiActionBar();

        txtScore = (TextView)findViewById(R.id.txtScore);
        txtResult = (TextView)findViewById(R.id.txtResult);
        txtGreetings = (TextView)findViewById(R.id.txtGreetings);

        double score = getIntent().getExtras().getDouble("score",0);
        txtScore.setText("Score : " + score);
        blinkText();
        if(score <50)
        {
            txtScore.setTextColor(Color.RED);
            txtResult.setText("Fail");
            txtGreetings.setText("Bad Luck!!!");
        }
        else
        {
            txtScore.setTextColor(Color.GREEN);
            txtResult.setText("Pass");
            txtGreetings.setText("Congratulation!!!");
        }
        //Toast.makeText(this,"Score : " + score,Toast.LENGTH_LONG).show();
    }

    private void intiActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void blinkText(){

        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        txtGreetings.setAnimation(anim);
    }

}
