package com.inkubator.adryan.learnarabic.rest;


import com.inkubator.adryan.learnarabic.model.Materi;
import com.inkubator.adryan.learnarabic.model.MateriDetail;

import java.util.List;

/**
 * Created by adryanev on 05/10/17.
 */

public class ResponseMateriDetail {

   private List<MateriDetail> materiDetail = null;

    public ResponseMateriDetail(List<MateriDetail> materiDetail){
        this.materiDetail = materiDetail;
    }

    public List<MateriDetail> getMateriDetails(){
        return this.materiDetail;
    }
}
