package com.inkubator.adryan.learnarabic.model;

/**
 * Created by adryanev on 05/10/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.inkubator.adryan.learnarabic.config.ServerConfig;

public class MateriDetail {

    @SerializedName("idMateriDetail")
    @Expose
    private Integer idMateriDetail;
    @SerializedName("idKategori")
    @Expose
    private Integer idKategori;
    @SerializedName("idMateri")
    @Expose
    private Integer idMateri;
    @SerializedName("isi")
    @Expose
    private String isi;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("terjemahan")
    @Expose
    private String terjemahan;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Integer getIdMateriDetail() {
        return idMateriDetail;
    }

    public void setIdMateriDetail(Integer idMateriDetail) {
        this.idMateriDetail = idMateriDetail;
    }

    public Integer getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Integer idKategori) {
        this.idKategori = idKategori;
    }

    public Integer getIdMateri() {
        return idMateri;
    }

    public void setIdMateri(Integer idMateri) {
        this.idMateri = idMateri;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getTerjemahan() {
        return terjemahan;
    }

    public void setTerjemahan(String terjemahan) {
        this.terjemahan = terjemahan;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
