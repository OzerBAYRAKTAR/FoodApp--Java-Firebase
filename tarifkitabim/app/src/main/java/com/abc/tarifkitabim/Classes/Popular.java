package com.abc.tarifkitabim.Classes;

import java.io.Serializable;

public class Popular implements Serializable {

    private String popular_ad;
    private String popular_resim;

    public Popular() {
    }

    public Popular(String popular_ad, String popular_resim) {
        this.popular_ad = popular_ad;
        this.popular_resim = popular_resim;
    }

    public String getPopular_ad() {
        return popular_ad;
    }

    public void setPopular_ad(String popular_ad) {
        this.popular_ad = popular_ad;
    }

    public String getPopular_resim() {
        return popular_resim;
    }

    public void setPopular_resim(String popular_resim) {
        this.popular_resim = popular_resim;
    }
}
