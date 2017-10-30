package com.inkubator.adryan.learnarabic.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.adapter.MateriDetailAdapter;
import com.inkubator.adryan.learnarabic.database.DbHelper;
import com.inkubator.adryan.learnarabic.model.MateriDetail;
import com.inkubator.adryan.learnarabic.rest.ApiClient;
import com.inkubator.adryan.learnarabic.rest.ApiInterface;
import com.inkubator.adryan.learnarabic.response.ResponseMateriDetail;

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

        Intent i = getIntent();
        Integer idSubMateri = i.getIntExtra("idSubMateri",0);
          //  getSoal();

        getMateriDetailFromDB(idSubMateri);

        Integer idKategori = dbHelper.getIdKategoriFromSubMateri(idSubMateri);
        Integer idMateri = dbHelper.getIdMateriFromSubMateri(idSubMateri);
        String namaKategori = dbHelper.getNamaKategori(idKategori);
        String namaMateri = dbHelper.getNamaMateri(idMateri);
        getSupportActionBar().setTitle(namaMateri+" - "+namaKategori);
    }

    private void getMateriDetailFromDB(int idSubMateri) {

        List<MateriDetail> md = dbHelper.getMateriDetailBySubMateri(idSubMateri);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_materi_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new MateriDetailAdapter(md,getApplicationContext()));


    }


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

    public boolean checkConnectivity(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo !=null && networkInfo.isConnected()) return true;
        else return false;

    }
}
