package com.abc.tarifkitabim.Classes;

import java.io.Serializable;

public class EklemeClass implements Serializable {

    private int tarif_id;
    private String tarif_ad;
    private String tarif_malzeme;
    private String tarif_yapilis;

    public EklemeClass() {
    }

    public EklemeClass(int tarif_id, String tarif_ad, String tarif_malzeme, String tarif_yapilis) {
        this.tarif_id = tarif_id;
        this.tarif_ad = tarif_ad;
        this.tarif_malzeme = tarif_malzeme;
        this.tarif_yapilis = tarif_yapilis;
    }

    public int getTarif_id() {
        return tarif_id;
    }

    public void setTarif_id(int tarif_id) {
        this.tarif_id = tarif_id;
    }

    public String getTarif_ad() {
        return tarif_ad;
    }

    public void setTarif_ad(String tarif_ad) {
        this.tarif_ad = tarif_ad;
    }

    public String getTarif_malzeme() {
        return tarif_malzeme;
    }

    public void setTarif_malzeme(String tarif_malzeme) {
        this.tarif_malzeme = tarif_malzeme;
    }

    public String getTarif_yapilis() {
        return tarif_yapilis;
    }

    public void setTarif_yapilis(String tarif_yapilis) {
        this.tarif_yapilis = tarif_yapilis;
    }
}
