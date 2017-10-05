package com.inkubator.adryan.learnarabic.rest;

/**
 * Created by adryanev on 05/10/17.
 */
import com.inkubator.adryan.learnarabic.model.Materi;
import com.inkubator.adryan.learnarabic.model.Soal;
import com.inkubator.adryan.learnarabic.response.ResponseMateri;
import com.inkubator.adryan.learnarabic.response.ResponseMateriDetail;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("materi-detail")
    Call<ResponseMateriDetail> getMateriDetails();
    @GET("materi-detail/detail")
    Call<ResponseMateriDetail> getMateriDetailByIdMateri(@QueryMap HashMap<String,Integer> idMateri);
    @GET("materi")
    Call<ResponseMateri> getMateri();

    @GET("soal")
    Call<Soal> getSoal();

}
