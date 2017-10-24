package com.inkubator.adryan.learnarabic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.adapter.MateriDetailAdapter;
import com.inkubator.adryan.learnarabic.database.DbHelper;
import com.inkubator.adryan.learnarabic.model.Materi;
import com.inkubator.adryan.learnarabic.model.MateriDetail;
import com.inkubator.adryan.learnarabic.rest.ApiClient;
import com.inkubator.adryan.learnarabic.rest.ApiInterface;
import com.inkubator.adryan.learnarabic.response.ResponseMateriDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adryanev on 05/10/17.
 */

public class MateriDetailActivity extends AppCompatActivity {

    private  static final String TAG = MainActivity.class.getSimpleName();
    DbHelper dbHelper;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_detail_recycle_view);
        dbHelper = new DbHelper(getApplicationContext());

        getSoalFromDB();
        //getSoal();
    }

    private void getSoalFromDB() {
        Intent i = getIntent();
        Integer idSubMateri = i.getIntExtra("idSubMateri",0);
        List<MateriDetail> md = dbHelper.getMateriDetailBySubMateri(idSubMateri);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_materi_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new MateriDetailAdapter(md,getApplicationContext()));


    }

    /*
    private void getSoal() {
        Intent intent = getIntent();
        Integer idMateri = intent.getIntExtra("idSubMateri",0);
        HashMap<String, Integer> params = new HashMap<>();
        params.put("idMateri",idMateri);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_materi_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseMateriDetail> call = apiService.getAllMateriDetail();
        call.enqueue(new Callback<ResponseMateriDetail>() {
            @Override
            public void onResponse(Call<ResponseMateriDetail> call, Response<ResponseMateriDetail> response) {

                if(response.isSuccessful()){
                    List<MateriDetail> materiDetails = response.body().getMateriDetails();
                    Log.d(TAG,"Succes receiving: "+materiDetails.size());

                    recyclerView.setAdapter(new MateriDetailAdapter(materiDetails,getApplicationContext()));
                }
                else{
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.d(TAG,"Gagal, Error Code ="+statusCode);
                }

            }

            @Override
            public void onFailure(Call<ResponseMateriDetail> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
    */
}
