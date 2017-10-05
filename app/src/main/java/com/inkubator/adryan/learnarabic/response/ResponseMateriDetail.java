package com.inkubator.adryan.learnarabic.response;


import com.google.gson.annotations.SerializedName;
import com.inkubator.adryan.learnarabic.model.Materi;
import com.inkubator.adryan.learnarabic.model.MateriDetail;

import java.util.List;

/**
 * Created by adryanev on 05/10/17.
 */

public class ResponseMateriDetail {

    @SerializedName("master")
   private List<MateriDetail> materiDetail = null;

    public ResponseMateriDetail(List<MateriDetail> materiDetail){
        this.materiDetail = materiDetail;
    }

    public List<MateriDetail> getMateriDetails(){
        return this.materiDetail;
    }
}
