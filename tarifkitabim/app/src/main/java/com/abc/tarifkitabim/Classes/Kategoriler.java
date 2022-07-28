package com.abc.tarifkitabim.Classes;

import java.io.Serializable;
import java.util.List;

public class Kategoriler implements Serializable {

    private String kategori_ad;
    private String kategori_resim;

    public Kategoriler() {
    }

    public Kategoriler(String kategori_ad, String kategori_resim) {
        this.kategori_ad = kategori_ad;
        this.kategori_resim = kategori_resim;
    }

    public String getKategori_ad() {
        return kategori_ad;
    }

    public void setKategori_ad(String kategori_ad) {
        this.kategori_ad = kategori_ad;
    }

    public String getKategori_resim() {
        return kategori_resim;
    }

    public void setKategori_resim(String kategori_resim) {
        this.kategori_resim = kategori_resim;
    }
}
