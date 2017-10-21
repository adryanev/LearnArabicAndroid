package com.inkubator.adryan.learnarabic.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.inkubator.adryan.learnarabic.model.Soal;

import java.util.List;

/**
 * Created by Adryan Eka Vandra on 10/6/2017.
 */

public class ResponseSoal {

    @SerializedName("master")
    @Expose
    private List<Soal> soal = null;

    public ResponseSoal(List<Soal> soal){
        this.soal = soal;
    }
    public List<Soal> getSoal() {
        return soal;
    }
}
