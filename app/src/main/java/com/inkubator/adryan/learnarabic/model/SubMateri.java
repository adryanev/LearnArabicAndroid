package com.inkubator.adryan.learnarabic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by adryanev on 22/10/17.
 */

public class SubMateri {

    @SerializedName("idSubMateri")
    @Expose
    private Integer idSubMateri;
    @SerializedName("idMateri")
    @Expose
    private Integer idMateri;
    @SerializedName("idKategori")
    @Expose
    private Integer idKategori;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Integer getIdSubMateri() {
        return idSubMateri;
    }

    public void setIdSubMateri(Integer idSubMateri) {
        this.idSubMateri = idSubMateri;
    }

    public Integer getIdMateri() {
        return idMateri;
    }

    public void setIdMateri(Integer idMateri) {
        this.idMateri = idMateri;
    }

    public Integer getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Integer idKategori) {
        this.idKategori = idKategori;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
