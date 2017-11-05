package com.inkubator.adryan.learnarabic.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.inkubator.adryan.learnarabic.model.Ujian;

import java.util.List;

/**
 * Created by adryanev on 12/10/17.
 */

public class ResponseUjian {

    @SerializedName("response")
    @Expose
    private List<Ujian> ujian = null;

    public void setUjian(){

    }
}
