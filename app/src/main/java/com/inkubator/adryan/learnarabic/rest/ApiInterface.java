package com.inkubator.adryan.learnarabic.rest;

/**
 * Created by adryanev on 05/10/17.
 */
import com.inkubator.adryan.learnarabic.model.Materi;
import com.inkubator.adryan.learnarabic.model.MateriDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("materi-detail")
    Call<ResponseMateriDetail> getMateriDetails();
    @GET("materi")
    Call<Materi> getMateri();

}
