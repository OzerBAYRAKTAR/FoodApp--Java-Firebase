package com.abc.tarifkitabim.Classes;

import java.io.Serializable;

public class Sureler implements Serializable {

    private String sure_id;
    private String sure_dk;

    public Sureler() {
    }

    public Sureler(String sure_id, String sure_dk) {
        this.sure_id = sure_id;
        this.sure_dk = sure_dk;
    }

    public String getSure_id() {
        return sure_id;
    }

    public void setSure_id(String sure_id) {
        this.sure_id = sure_id;
    }

    public String getSure_dk() {
        return sure_dk;
    }

    public void setSure_dk(String sure_dk) {
        this.sure_dk = sure_dk;
    }
}
