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
    @SerializedName("idSubMateri")
    @Expose
    private Integer idSubMateri;
    @SerializedName("isi")
    @Expose
    private String isi;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("suara")
    @Expose
    private String suara;
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

    public Integer getIdSubMateri() {
        return idSubMateri;
    }

    public void setIdSubMateri(Integer idSubMateri) {
        this.idSubMateri = idSubMateri;
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

    public String getSuara() {
        return suara;
    }
    public void setSuara(String suara){
        this.suara = suara;
    }
}
