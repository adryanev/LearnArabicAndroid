package com.inkubator.adryan.learnarabic.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.inkubator.adryan.learnarabic.model.Materi;
import com.inkubator.adryan.learnarabic.model.Soal;

import java.util.List;

/**
 * Created by Adryan Eka Vandra on 10/6/2017.
 */

public class ResponseMateri {

    @SerializedName("master")
    @Expose
    private List<Materi> materi = null;

    public ResponseMateri(List<Materi> materi){
        this.materi = materi;
    }
    public List<Materi> getMateri() {
        return materi;
    }
}
