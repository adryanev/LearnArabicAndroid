package com.inkubator.adryan.learnarabic.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.inkubator.adryan.learnarabic.model.User;

import java.util.List;

/**
 * Created by adryanev on 22/10/17.
 */

public class ResponseLogin {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<User> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

}
