package com.inkubator.adryan.learnarabic.model;

/**
 * Created by adryanev on 12/10/17.
 */

public class Ujian {

    private Integer idUjian;
    private Integer idUser;
    private Integer totalSkor;
    private Integer sync;

    public Integer getIdUjian() {
        return idUjian;
    }

    public void setIdUjian(Integer idUjian) {
        this.idUjian = idUjian;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getTotalSkor() {
        return totalSkor;
    }

    public void setTotalSkor(Integer totalSkor) {
        this.totalSkor = totalSkor;
    }

    public Integer getSync() {
        return sync;
    }

    public void setSync(Integer sync) {
        this.sync = sync;
    }
}
