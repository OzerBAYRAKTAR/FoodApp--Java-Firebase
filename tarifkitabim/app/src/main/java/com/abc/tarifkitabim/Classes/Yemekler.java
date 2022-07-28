package com.abc.tarifkitabim.Classes;

import java.io.Serializable;

public class Yemekler implements Serializable {


    private String yemek_id;
    private String yemek_ad;
    private String yemek_resim;
    private String kategori_ad;
    private String sure_dk;
    private String malzeme_text;
    private String yapilis_text;
    private String kackisi_adet;

    public Yemekler() {
    }

    public Yemekler(String yemek_id, String yemek_ad, String yemek_resim, String kategori_ad, String sure_dk, String malzeme_text, String yapilis_text, String kackisi_adet) {
        this.yemek_id = yemek_id;
        this.yemek_ad = yemek_ad;
        this.yemek_resim = yemek_resim;
        this.kategori_ad = kategori_ad;
        this.sure_dk = sure_dk;
        this.malzeme_text = malzeme_text;
        this.yapilis_text = yapilis_text;
        this.kackisi_adet = kackisi_adet;
    }

    public String getYemek_id() {
        return yemek_id;
    }

    public void setYemek_id(String yemek_id) {
        this.yemek_id = yemek_id;
    }

    public String getYemek_ad() {
        return yemek_ad;
    }

    public void setYemek_ad(String yemek_ad) {
        this.yemek_ad = yemek_ad;
    }

    public String getYemek_resim() {
        return yemek_resim;
    }

    public void setYemek_resim(String yemek_resim) {
        this.yemek_resim = yemek_resim;
    }

    public String getKategori_ad() {
        return kategori_ad;
    }

    public void setKategori_ad(String kategori_ad) {
        this.kategori_ad = kategori_ad;
    }

    public String getSure_dk() {
        return sure_dk;
    }

    public void setSure_dk(String sure_dk) {
        this.sure_dk = sure_dk;
    }

    public String getMalzeme_text() {
        return malzeme_text;
    }

    public void setMalzeme_text(String malzeme_text) {
        this.malzeme_text = malzeme_text;
    }

    public String getYapilis_text() {
        return yapilis_text;
    }

    public void setYapilis_text(String yapilis_text) {
        this.yapilis_text = yapilis_text;
    }

    public String getKackisi_adet() {
        return kackisi_adet;
    }

    public void setKackisi_adet(String kackisi_adet) {
        this.kackisi_adet = kackisi_adet;
    }
}
