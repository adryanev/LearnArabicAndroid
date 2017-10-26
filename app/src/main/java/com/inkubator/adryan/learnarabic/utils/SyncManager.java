package com.inkubator.adryan.learnarabic.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.inkubator.adryan.learnarabic.activity.HasilActivity;
import com.inkubator.adryan.learnarabic.config.ServerConfig;
import com.inkubator.adryan.learnarabic.database.DbHelper;
import com.inkubator.adryan.learnarabic.model.Kategori;
import com.inkubator.adryan.learnarabic.model.Materi;
import com.inkubator.adryan.learnarabic.model.MateriDetail;
import com.inkubator.adryan.learnarabic.model.Soal;
import com.inkubator.adryan.learnarabic.model.SubMateri;
import com.inkubator.adryan.learnarabic.model.Ujian;
import com.inkubator.adryan.learnarabic.response.ResponseKategori;
import com.inkubator.adryan.learnarabic.response.ResponseMateri;
import com.inkubator.adryan.learnarabic.response.ResponseMateriDetail;
import com.inkubator.adryan.learnarabic.response.ResponseSoal;
import com.inkubator.adryan.learnarabic.response.ResponseSubMateri;
import com.inkubator.adryan.learnarabic.rest.ApiClient;
import com.inkubator.adryan.learnarabic.rest.ApiInterface;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adryanev on 17/10/17.
 */

public class SyncManager extends ContextWrapper {

    private ProgressDialog pb;
    private static final String TAG = SyncManager.class.getSimpleName();


    private DbHelper db;
    public SyncManager(Context context){
        super(context);
        this.db = new DbHelper(this);
    }

    public void syncUjian(){
        List<Ujian> listIdUjian = db.getNotSyncListUjian();
        for(final Ujian u: listIdUjian){
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ResponseBody> call = apiService.addUjian(u.getIdUser().toString(), (int) Math.ceil(u.getTotalSkor()));
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "Success Mengirimkan data ujian ke server.");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e(TAG, "Gagal Sinkronisasi Ujian");
                }

            });
        }
    }
    public void syncAll(){
        pb = new ProgressDialog(this);
        pb.setCancelable(false);
        syncKategori();
        syncMateri();
        syncSubMater();
        syncMateriDetail();
        syncSoal();
        Toast.makeText(this,"Sinkronisasi selesai",Toast.LENGTH_SHORT).show();
        
    }

    private void syncSubMater() {
        pb.setMessage("Sinkronisasi Sub Materi");
        pb.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseSubMateri> call = apiService.getSubMateri();
        call.enqueue(new Callback<ResponseSubMateri>() {
            @Override
            public void onResponse(Call<ResponseSubMateri> call, Response<ResponseSubMateri> response) {

                if(response.isSuccessful()){
                    List<SubMateri> subMateri = response.body().getSubMateri();
                    Log.d(TAG,"Succes receiving: "+subMateri.size());
                    db.truncateTable("sub_materi");
                    for (SubMateri subMateri1: subMateri) {
                        db.createSubMateri(subMateri1);
                        Log.d(TAG,"Insert Sub materi "+subMateri1.getIdSubMateri().toString());

                    }
                    pb.dismiss();
                }
                else{
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.d(TAG,"Gagal, Error Code ="+statusCode);
                }

            }

            @Override
            public void onFailure(Call<ResponseSubMateri> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void syncMateriDetail() {
        pb.setMessage("Sinkronisasi Materi Detail");
        pb.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseMateriDetail> call = apiService.getAllMateriDetail();
        call.enqueue(new Callback<ResponseMateriDetail>() {
            @Override
            public void onResponse(Call<ResponseMateriDetail> call, Response<ResponseMateriDetail> response) {

                if(response.isSuccessful()){
                    List<MateriDetail> materiDetails = response.body().getMateriDetails();
                    Log.d(TAG,"Succes receiving: "+materiDetails.size());
                    db.truncateTable("materi_detail");
                    for (MateriDetail materidetail: materiDetails) {
                        db.createMateriDetail(materidetail);
                        Log.d(TAG,"Insert Materi detail "+materidetail.getIdMateriDetail().toString());

                    }

                    pb.dismiss();
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

    private void syncKategori() {
        pb.setMessage("Sinkronisasi Kategori");
        pb.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseKategori> call = apiService.getKategori();
        call.enqueue(new Callback<ResponseKategori>() {
            @Override
            public void onResponse(Call<ResponseKategori> call, Response<ResponseKategori> response) {

                if(response.isSuccessful()){
                    List<Kategori> kategoris = response.body().getKategoris();
                    Log.d(TAG,"Succes receiving: "+kategoris.size());
                    db.truncateTable("kategori");
                    for (Kategori kategori: kategoris) {
                        db.createKategori(kategori);
                        Log.d(TAG,"Insert Materi Detail "+kategori.getIdKategori().toString());
                    }
                    pb.dismiss();
                }
                else{
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.d(TAG,"Gagal, Error Code ="+statusCode);
                }

            }

            @Override
            public void onFailure(Call<ResponseKategori> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }

    private void syncMateri() {
        pb.setMessage("Sinkronisasi Materi");
        pb.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseMateri> call = apiService.getMateri();
        call.enqueue(new Callback<ResponseMateri>() {
            @Override
            public void onResponse(Call<ResponseMateri> call, Response<ResponseMateri> response) {

                if(response.isSuccessful()){
                    List<Materi> materi = response.body().getMateri();
                    Log.d(TAG,"Succes receiving: "+materi.size());
                    db.truncateTable("materi");
                    for (Materi materi1: materi) {
                        db.createMateri(materi1);
                        Log.d(TAG,"Insert Materi "+materi1.getIdMateri().toString());
                    }
                    pb.dismiss();
                }
                else{
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.d(TAG,"Gagal, Error Code ="+statusCode);
                }

            }

            @Override
            public void onFailure(Call<ResponseMateri> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void syncSoal() {
        pb.setMessage("Sinkronisasi Soal");
        pb.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseSoal> call = apiService.getSoal();
        call.enqueue(new Callback<ResponseSoal>() {
            @Override
            public void onResponse(Call<ResponseSoal> call, Response<ResponseSoal> response) {

                if(response.isSuccessful()){
                    List<Soal> soals = response.body().getSoal();
                    Log.d(TAG,"Succes receiving: "+soals.size());
                    db.truncateTable("soal");
                    for (Soal soal: soals) {
                        db.createSoal(soal);
                        Log.d(TAG,"Insert Soal "+soal.getIdSoal().toString());


                    }
                    pb.dismiss();
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
    @NonNull
    private Target picassoImageTarget(Context context, final String imageDir, final String imageName) {
        Log.d("picassoImageTarget", " picassoImageTarget");
        ContextWrapper cw = new ContextWrapper(context);
        final File directory = cw.getDir(imageDir, Context.MODE_PRIVATE); // path to /data/data/yourapp/app_imageDir
        return new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final File myImageFile = new File(directory, imageName); // Create image file
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(myImageFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.i("image", "image saved to >>>" + myImageFile.getAbsolutePath());

                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (placeHolderDrawable != null) {}
            }
        };
    }


}
