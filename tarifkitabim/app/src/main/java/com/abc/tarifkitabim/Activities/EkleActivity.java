package com.abc.tarifkitabim.Activities;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


import com.abc.tarifkitabim.Adapters.EklemeAdapter;
import com.abc.tarifkitabim.Classes.EklemeClass;
import com.abc.tarifkitabim.Classes.tarifEklemeDao;
import com.abc.tarifkitabim.R;
import com.abc.tarifkitabim.veritabaniTarifEkleme.dataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class EkleActivity extends AppCompatActivity {

    private EklemeAdapter eklemeAdapter;
    private RecyclerView ekle_rv;
    private FloatingActionButton fabEkle,fabevev;
    private ArrayList<EklemeClass> eklemeClassArrayList;
    private Context mContext;
    private dataBase vt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle);

        fabEkle=findViewById(R.id.fabEkle);
        fabevev=findViewById(R.id.fabevev);
        ekle_rv=findViewById(R.id.ekle_rv);

        vt=new dataBase(this);

        ekle_rv.setHasFixedSize(true);
        ekle_rv.setLayoutManager(new LinearLayoutManager(this));

        eklemeClassArrayList=new tarifEklemeDao().tumTarifler(vt);
        eklemeAdapter=new EklemeAdapter(this,eklemeClassArrayList,vt);
        ekle_rv.setAdapter(eklemeAdapter);




        fabEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertgoster();

            }
        });
        fabevev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EkleActivity.this,FirstActivity.class));
            }
        });



    }


    public void alertgoster(){

        LayoutInflater lai=LayoutInflater.from(this);
        View tasarim=lai.inflate(R.layout.alert_tasarimm,null);

        final EditText editTextAd=tasarim.findViewById(R.id.editTextAd);
        final EditText tvMalz=tasarim.findViewById(R.id.tvMalz);
        final EditText tvYap=tasarim.findViewById(R.id.tvYap);

        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        ad.setTitle("KİŞİ EKLEME EKRANI");
        ad.setView(tasarim);

        ad.setPositiveButton("EKLE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tarif_ad=editTextAd.getText().toString().trim();
                String tarif_malzeme=tvMalz.getText().toString().trim();
                String tarif_yapilis=tvYap.getText().toString().trim();

                new tarifEklemeDao().tarifEkle(vt,tarif_ad,tarif_malzeme,tarif_yapilis);

                eklemeClassArrayList=new tarifEklemeDao().tumTarifler(vt);

                eklemeAdapter=new EklemeAdapter(EkleActivity.this,eklemeClassArrayList,vt);
                ekle_rv.setAdapter(eklemeAdapter);


            }
        });
        ad.setNegativeButton("İPTAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ad.create().show();
    }

}