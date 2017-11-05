package com.inkubator.adryan.learnarabic.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.inkubator.adryan.learnarabic.model.Video;

import java.util.List;

/**
 * Created by adryanev on 05/11/17.
 */

public class ResponseVideo {
    @SerializedName("master")
    @Expose
    private List<Video> videoList = null;

    public List<Video> getVideo() {
        return videoList;
    }

    public void setMVideo(List<Video> videoList) {
        this.videoList = videoList;
    }
}
