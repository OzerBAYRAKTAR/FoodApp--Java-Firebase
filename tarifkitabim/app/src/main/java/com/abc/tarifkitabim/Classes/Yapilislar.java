package com.abc.tarifkitabim.Classes;

import java.io.Serializable;

public class Yapilislar implements Serializable {
    private String yapilis_id;
    private String yapilis_text;

    public Yapilislar() {
    }

    public Yapilislar(String yapilis_id, String yapilis_text) {
        this.yapilis_id = yapilis_id;
        this.yapilis_text = yapilis_text;
    }

    public String getYapilis_id() {
        return yapilis_id;
    }

    public void setYapilis_id(String yapilis_id) {
        this.yapilis_id = yapilis_id;
    }

    public String getYapilis_text() {
        return yapilis_text;
    }

    public void setYapilis_text(String yapilis_text) {
        this.yapilis_text = yapilis_text;
    }
}
