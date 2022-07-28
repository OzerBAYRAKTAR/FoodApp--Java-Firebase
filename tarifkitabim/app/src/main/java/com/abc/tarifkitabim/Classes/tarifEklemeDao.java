package com.abc.tarifkitabim.Classes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.abc.tarifkitabim.veritabaniTarifEkleme.dataBase;

import java.util.ArrayList;

public class tarifEklemeDao {

    public ArrayList<EklemeClass> tumTarifler(dataBase vt) {
        ArrayList<EklemeClass> eklemeClassArrayList = new ArrayList<>();

        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT *FROM tarifekleme", null);

        while (c.moveToNext()) {
            EklemeClass ek = new EklemeClass(c.getInt(c.getColumnIndexOrThrow("tarif_id"))
                    ,c.getString(c.getColumnIndexOrThrow("tarif_ad"))
                    ,c.getString(c.getColumnIndexOrThrow("tarif_malzeme"))
                    ,c.getString(c.getColumnIndexOrThrow("tarif_yapilis")));

            eklemeClassArrayList.add(ek);

        }
        dbx.close();
        return eklemeClassArrayList;
    }
    public void tarifSil(dataBase vt,int tarif_id){
        SQLiteDatabase db=vt.getWritableDatabase();
        db.delete("tarifekleme","tarif_id=?",new String[]{String.valueOf(tarif_id)});
        db.close();
    }
    public void tarifEkle(dataBase vt,String tarif_ad,String tarif_malzeme,String tarif_yapilis){
        SQLiteDatabase db=vt.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("tarif_ad",tarif_ad);
        values.put("tarif_malzeme",tarif_malzeme);
        values.put("tarif_yapilis",tarif_yapilis);

        db.insertOrThrow("tarifekleme",null,values);
        db.close();
    }
    public void tarifguncelle(dataBase vt,int tarif_id,String tarif_ad,String tarif_malzeme,String tarif_yapilis){
        SQLiteDatabase db=vt.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("tarif_ad",tarif_ad);
        values.put("tarif_malzeme",tarif_malzeme);
        values.put("tarif_yapilis",tarif_yapilis);

        db.update("tarifekleme",values,"tarif_id=?",new String[]{String.valueOf(tarif_id)});
        db.close();
    }
}




