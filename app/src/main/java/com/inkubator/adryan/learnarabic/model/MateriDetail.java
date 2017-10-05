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

    public MateriDetail(Integer idMateriDetail, Integer idMateri, String isi, String gambar, String terjemahan){
        this.idMateriDetail = idMateriDetail;
        this.idMateri = idMateri;
        this.isi = isi;
        this.gambar = gambar;
        this.terjemahan = terjemahan;
    }

    public Integer getIdMateriDetail(){
        return this.idMateriDetail;
    }

    public Integer getIdMateri() {
        return idMateri;
    }

    public String getGambar() {
        String url = ServerConfig.IMAGE_FOLDER;
        return url+gambar;
    }

    public String getIsi() {
        return isi;
    }

    public String getTerjemahan() {
        return terjemahan;
    }
}
