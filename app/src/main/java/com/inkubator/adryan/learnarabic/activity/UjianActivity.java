package com.inkubator.adryan.learnarabic.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.BidiFormatter;
import android.text.Html;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.adapter.MateriDetailAdapter;
import com.inkubator.adryan.learnarabic.config.ServerConfig;
import com.inkubator.adryan.learnarabic.database.DbHelper;
import com.inkubator.adryan.learnarabic.model.MateriDetail;
import com.inkubator.adryan.learnarabic.model.Soal;
import com.inkubator.adryan.learnarabic.response.ResponseMateriDetail;
import com.inkubator.adryan.learnarabic.response.ResponseSoal;
import com.inkubator.adryan.learnarabic.response.ResponseUjian;
import com.inkubator.adryan.learnarabic.rest.ApiClient;
import com.inkubator.adryan.learnarabic.rest.ApiInterface;
import com.inkubator.adryan.learnarabic.utils.SessionManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Adryan Eka Vandra on 10/6/2017.
 */

public class UjianActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences s;
    SessionManager sm;
    Context context;
    TextView timer, textSoal, noSoal;
    Button a,b,c,d;
    ProgressBar pb;
    CountDownTimer countDownTimer;
    Animation animation;
    ProgressDialog pd;
    Soal soalSekarang;
    static Integer score;
    static int currentQue;
    List<Soal> listSoalUjian;
    ImageView gambarSoal;
    DbHelper db;
    private static final Integer SYNCHED_FALSE = 0;
    private static final Integer SYNCHED_TRUE = 1;
    private  static final String TAG = UjianActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ujian);
        db = new DbHelper(getApplicationContext());
        initView();
        initSoal();
        //initSoalFromDB();
        initTimer();
        blinkText();
        getSupportActionBar().setTitle("Ujian");

    }

    private void initSoalFromDB() {
        listSoalUjian = db.getSoalByLimit((long) 5);
        pd.dismiss();
        getNextQuestion();


    }

    private void initTimer() {
        countDownTimer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long sec = millisUntilFinished/1000;
                timer.setText(sec + "Detik");
                timer.setTextColor(Color.BLACK);
                if(sec<=5){
                    timer.setTextColor(Color.RED);
                    animation.setRepeatCount(Animation.INFINITE);
                    blinkText();
                }else{
                    animation.setRepeatCount(0);
                }

            }

            @Override
            public void onFinish() {
                timer.setText("Waktu Habis");
                getNextQuestion();
            }
        };
    }

    private void getNextQuestion() {

        if(currentQue<listSoalUjian.size()){
            currentQue++;
            pb.setProgress((currentQue*100)/listSoalUjian.size());
            noSoal.setText(currentQue+"/"+listSoalUjian.size());
            soalSekarang = listSoalUjian.get(currentQue-1);

            textSoal.setText(Html.fromHtml(soalSekarang.getSoal()));
            Picasso.with(getApplicationContext()).load(ServerConfig.IMAGE_FOLDER+soalSekarang.getGambar()).resize(300,300).into(gambarSoal);

            a.setText("A. "+soalSekarang.getA());
            b.setText("B. "+soalSekarang.getB());
            c.setText("C. "+soalSekarang.getC());
            d.setText("D. "+soalSekarang.getD());

            a.setOnClickListener(this);
            b.setOnClickListener(this);
            c.setOnClickListener(this);
            d.setOnClickListener(this);

            countDownTimer.start();
        }else{
            pd.setMessage("Sedang menyiapkan Hasil...");
            pd.show();
            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(3000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                pd.dismiss();
                                finish();
                                sendResult();
                            }
                        });
                    }
                }
            };
            timerThread.start();
        }
    }

    private void sendResult() {
        sm = new SessionManager(getApplicationContext());
        final HashMap<String,String> user = sm.getUserDetail();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.d(TAG,"idUser = "+user.get("idUser")+" Score: "+score);
        Call<ResponseBody> call = apiService.addUjian(user.get("idUser"), score.toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Log.d(TAG,response.message());
                    Log.d(TAG,"Success Mengirimkan data ujian ke server.");

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG,"Gagal mengirimkan data ujian ke server");
                Log.d(TAG,"Memasukkan data ujian ke data tunggu");
                db.setUjian(Integer.parseInt(user.get("idUser")),(int) Math.ceil(score),SYNCHED_FALSE);
                Log.d(TAG,"Memasukkan ujian ke local database");
            }
        });
        Intent i = new Intent(getApplicationContext(),HasilActivity.class);
        i.putExtra("score",score);
        i.putExtra("idUser",user.get("idUser"));
        startActivity(i);
        finish();

    }

    private void blinkText() {
        animation = new AlphaAnimation(0.0f,1.0f);
        animation.setDuration(50);
        animation.setStartOffset(20);
        animation.setRepeatCount(Animation.INFINITE);
        timer.setAnimation(animation);
    }

    private void initView() {
        context = this.getApplicationContext();
        pd = ProgressDialog.show(this,null,"Sedang menyiapkan soal ujian",true,false);

        score = 0;
        currentQue = 0;
        timer = (TextView) findViewById(R.id.text_timer);
        pb = (ProgressBar) findViewById(R.id.progress_bar);
        noSoal = (TextView) findViewById(R.id.tv_noSoal);

        gambarSoal = (ImageView) findViewById(R.id.gambarSoal);
        textSoal = (TextView) findViewById(R.id.textSoal);
        timer.setText("00 Detik");

        a = (Button) findViewById(R.id.jawabanA);
        b = (Button) findViewById(R.id.jawabanB);
        c = (Button) findViewById(R.id.jawabanC);
        d = (Button) findViewById(R.id.jawabanD);


    }

    private void initSoal() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseSoal> call = apiService.getSoal();
        call.enqueue(new Callback<ResponseSoal>() {
            @Override
            public void onResponse(Call<ResponseSoal> call, Response<ResponseSoal> response) {

                if(response.isSuccessful()){
                    List<Soal> soalUjian = response.body().getSoal();
                    Log.d(TAG,"Succes receiving: "+soalUjian.size());
                    listSoalUjian = soalUjian;
                    pd.dismiss();
                    getNextQuestion();
                }
                else{
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.d(TAG,"Gagal, Error Code ="+statusCode);
                }

            }

            @Override
            public void onFailure(Call<ResponseSoal> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        String ans = "";
        switch (view.getId()){

            case R.id.jawabanA:
                ans = a.getText().toString().substring(0,1);

                break;
            case R.id.jawabanB:
                ans = b.getText().toString().substring(0,1);
                break;
            case R.id.jawabanC:
                ans = c.getText().toString().substring(0,1);
                break;
            case R.id.jawabanD:
                ans = d.getText().toString().substring(0,1);
                break;
        }

        if(ans.equalsIgnoreCase(soalSekarang.getJawaban())){
            score +=100/listSoalUjian.size();
        }

        countDownTimer.cancel();
        pd.show();
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            pd.hide();
                            getNextQuestion();
                        }
                    });
                }
            }
        };
        timerThread.start();

        Log.d("RES", "onCheckedChanged: " + ans + "[" + score + "]");

    }

}
