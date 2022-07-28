package com.abc.tarifkitabim.Classes;

import java.io.Serializable;

public class Kackisi implements Serializable {
    private String kackisi_id;
    private String kackisi_adet;

    public Kackisi() {
    }

    public Kackisi(String kackisi_id, String kackisi_adet) {
        this.kackisi_id = kackisi_id;
        this.kackisi_adet = kackisi_adet;
    }

    public String getKackisi_id() {
        return kackisi_id;
    }

    public void setKackisi_id(String kackisi_id) {
        this.kackisi_id = kackisi_id;
    }

    public String getKackisi_adet() {
        return kackisi_adet;
    }

    public void setKackisi_adet(String kackisi_adet) {
        this.kackisi_adet = kackisi_adet;
    }
}
