package com.inkubator.adryan.learnarabic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by adryanev on 05/11/17.
 */

public class Video {
    @SerializedName("idVideo")
    @Expose
    private Integer idVideo;
    @SerializedName("idYoutubeVideo")
    @Expose
    private String idYoutubeVideo;
    @SerializedName("namaVideo")
    @Expose
    private String namaVideo;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Integer getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(Integer idVideo) {
        this.idVideo = idVideo;
    }

    public String getIdYoutubeVideo() {
        return idYoutubeVideo;
    }

    public void setIdYoutubeVideo(String idYoutubeVideo) {
        this.idYoutubeVideo = idYoutubeVideo;
    }

    public String getNamaVideo() {
        return namaVideo;
    }

    public void setNamaVideo(String namaVideo) {
        this.namaVideo = namaVideo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
