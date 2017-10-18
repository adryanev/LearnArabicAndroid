package com.inkubator.adryan.learnarabic.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.inkubator.adryan.learnarabic.model.Kategori;

import java.util.List;

/**
 * Created by adryanev on 17/10/17.
 */

public class ResponseKategori {

    @SerializedName("master")
    @Expose
    private List<Kategori> kategoris = null;

    public List<Kategori> getKategoris() {
        return kategoris;
    }

    public ResponseKategori(List<Kategori> kategoris){
        this.kategoris  = kategoris;
    }
    public void setMaster(List<Kategori> kategoris) {
        this.kategoris = kategoris;
    }
}
