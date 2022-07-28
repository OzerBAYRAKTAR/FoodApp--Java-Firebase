package com.abc.tarifkitabim.Classes;

import java.io.Serializable;

public class Malzemeler implements Serializable {
    private String malzeme_id;
    private String malzeme_text;


    public Malzemeler() {
    }

    public Malzemeler(String malzeme_id, String malzeme_text) {
        this.malzeme_id = malzeme_id;
        this.malzeme_text = malzeme_text;
    }

    public String getMalzeme_id() {
        return malzeme_id;
    }

    public void setMalzeme_id(String malzeme_id) {
        this.malzeme_id = malzeme_id;
    }

    public String getMalzeme_text() {
        return malzeme_text;
    }

    public void setMalzeme_text(String malzeme_text) {
        this.malzeme_text = malzeme_text;
    }
}
