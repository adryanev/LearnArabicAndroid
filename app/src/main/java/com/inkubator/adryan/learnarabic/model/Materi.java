package com.inkubator.adryan.learnarabic.model;

/**
 * Created by adryanev on 05/10/17.
 */
import com.google.gson.annotations.SerializedName;
public class Materi {

    @SerializedName("idMateri")
    private Integer idMateri;
    @SerializedName("namaMateri")
    private String namaMateri;
    @SerializedName("idKategori")
    private Integer idKategori;

    public Materi(Integer idMateri, String namaMateri, Integer idKategori){
        this.idMateri = idMateri;
        this.namaMateri = namaMateri;
        this.idKategori = idKategori;
    }

    public Integer getIdMateri(){
        return this.idMateri;
    }
    public String getNamaMateri(){
        return this.namaMateri;
    }
    public Integer getIdKategori(){
        return this.idKategori;
    }

}
