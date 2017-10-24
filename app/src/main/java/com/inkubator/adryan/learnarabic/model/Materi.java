package com.inkubator.adryan.learnarabic.model;

/**
 * Created by adryanev on 05/10/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

public class Materi {


    @SerializedName("idMateri")
    @Expose
    private Integer idMateri;
    @SerializedName("namaMateri")
    @Expose
    private String namaMateri;
    @SerializedName("idKategori")
    @Expose
    private String idKategori;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Integer getIdMateri() {
        return idMateri;
    }

    public void setIdMateri(Integer idMateri) {
        this.idMateri = idMateri;
    }

    public String getNamaMateri() {
        return namaMateri;
    }

    public void setNamaMateri(String namaMateri) {
        this.namaMateri = namaMateri;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
