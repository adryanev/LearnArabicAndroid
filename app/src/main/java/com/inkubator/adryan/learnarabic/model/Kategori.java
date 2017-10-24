package com.inkubator.adryan.learnarabic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by adryanev on 17/10/17.
 */

public class Kategori {

    @SerializedName("idKategori")
    @Expose
    private Integer idKategori;
    @SerializedName("namaKategori")
    @Expose
    private String namaKategori;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;


    public Integer getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Integer idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
