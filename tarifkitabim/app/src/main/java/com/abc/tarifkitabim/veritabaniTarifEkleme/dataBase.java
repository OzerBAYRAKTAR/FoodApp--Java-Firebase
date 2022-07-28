package com.abc.tarifkitabim.veritabaniTarifEkleme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dataBase extends SQLiteOpenHelper {
    public dataBase(@Nullable Context context) {
        super(context, "tarifler.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE tarifekleme (tarif_id INTEGER PRIMARY KEY AUTOINCREMENT,tarif_ad TEXT,tarif_malzeme TEXt,tarif_yapilis TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS tarifekleme");
        onCreate(db);

    }
}
