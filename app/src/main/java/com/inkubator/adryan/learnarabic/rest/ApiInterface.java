package com.inkubator.adryan.learnarabic.rest;

/**
 * Created by adryanev on 05/10/17.
 */
import com.inkubator.adryan.learnarabic.model.Materi;
import com.inkubator.adryan.learnarabic.model.Soal;
import com.inkubator.adryan.learnarabic.response.ResponseKategori;
import com.inkubator.adryan.learnarabic.response.ResponseLogin;
import com.inkubator.adryan.learnarabic.response.ResponseMateri;
import com.inkubator.adryan.learnarabic.response.ResponseMateriDetail;
import com.inkubator.adryan.learnarabic.response.ResponseSoal;
import com.inkubator.adryan.learnarabic.response.ResponseSubMateri;
import com.inkubator.adryan.learnarabic.response.ResponseUjian;
import com.inkubator.adryan.learnarabic.response.ResponseVideo;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("materi-detail/detail")
    Call<ResponseMateriDetail> getMateriDetailByIdMateri(@QueryMap HashMap<String,Integer> idMateri);

    @GET("materi-detail")
    Call<ResponseMateriDetail> getAllMateriDetail();
    @GET("materi")
    Call<ResponseMateri> getMateri();
    @GET("soal/random")
    Call<ResponseSoal> getSoal();
    @GET("kategori")
    Call<ResponseKategori> getKategori();
    @GET("sub-materi")
    Call<ResponseSubMateri> getSubMateri();
    @GET("video")
    Call<ResponseVideo> getVideo();

    @FormUrlEncoded
    @POST("register/register-user")
    Call<ResponseBody> registerRequest(
            @Field("nama") String nama,
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("tanggalLahir")String tanggalLahir);

    @FormUrlEncoded
    @POST("login/login")
    Call<ResponseLogin> loginRequest(@Field("username") String username,
                                     @Field("password") String password);

    @FormUrlEncoded
    @POST("ujian/add")
    Call<ResponseBody> addUjian(@Field("idUser") String idUser,
                                 @Field("totalSkor") String totalSkor);
}
