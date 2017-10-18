package com.inkubator.adryan.learnarabic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.inkubator.adryan.learnarabic.config.ServerConfig;

/**
 * Created by Adryan Eka Vandra on 10/6/2017.
 */

public class Soal {

    @SerializedName("idSoal")
    @Expose
    private Integer idSoal;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("soal")
    @Expose
    private String soal;
    @SerializedName("a")
    @Expose
    private String a;
    @SerializedName("b")
    @Expose
    private String b;
    @SerializedName("c")
    @Expose
    private String c;
    @SerializedName("d")
    @Expose
    private String d;
    @SerializedName("jawaban")
    @Expose
    private String jawaban;

    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Integer getIdSoal(){
        return this.idSoal;
    }

    public void setIdSoal(Integer idSoal){this.idSoal = idSoal;}
    public void setGambar(String gambar){this.gambar = gambar;}
    public void setSoal(String soal){this.soal = soal;}
    public void setA(String a){this.a = a;}
    public void setB(String b){this.b = b;}
    public void setC(String c){this.c = c;}
    public void setD(String d){this.d = d;}
    public void setJawaban(String jawaban){this.jawaban = jawaban;}
    public void setTimestamp(String timestamp){this.timestamp = timestamp;}

    public String getSoal(){
        return this.soal;
    }
    public String getA(){
        return this.a;
    }
    public String getB(){
        return this.b;
    }
    public String getC(){
        return this.c;
    }
    public String getD(){
        return this.d;
    }
    public String getJawaban(){
        return this.jawaban;
    }
    public String getGambar(){return this.gambar;}
    public String getTimestamp(){return this.timestamp;}
}
