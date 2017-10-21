package com.inkubator.adryan.learnarabic.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.inkubator.adryan.learnarabic.model.SubMateri;

import java.util.List;

/**
 * Created by adryanev on 22/10/17.
 */

public class ResponseSubMateri {
    @SerializedName("master")
    @Expose
    private List<SubMateri> subMateri = null;

    public List<SubMateri> getSubMateri() {
        return subMateri;
    }

    public void setMaster(List<SubMateri> master) {
        this.subMateri = master;
    }
}
