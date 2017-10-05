package com.inkubator.adryan.learnarabic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Adryan Eka Vandra on 10/6/2017.
 */

public class Soal {

    @SerializedName("idSoal")
    @Expose
    private Integer idSoal;
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

    public Integer getIdSoal(){
        return this.idSoal;
    }

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
}
