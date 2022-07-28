package com.abc.tarifkitabim.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import com.abc.tarifkitabim.Adapters.YemeklerAdapter;
import com.abc.tarifkitabim.Classes.Kategoriler;
import com.abc.tarifkitabim.Classes.Yemekler;
import com.abc.tarifkitabim.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class YemeklerActivity extends AppCompatActivity {
    private Toolbar tb2;
    private RecyclerView yemek_rv;
    private FloatingActionButton Evfab;
    private ArrayList<Yemekler> yemeklerArrayList;
    private YemeklerAdapter adapter;
    private Kategoriler kategoriler;
    private FirebaseDatabase database;
    private DatabaseReference myRefYemekler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemekler);

        tb2=findViewById(R.id.tb2);
        yemek_rv=findViewById(R.id.yemek_rv);
        Evfab=findViewById(R.id.Evfab);






        Evfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(YemeklerActivity.this,FirstActivity.class);
                startActivity(intent);
            }
        });

        kategoriler=(Kategoriler) getIntent().getSerializableExtra("kategoriNesne");

        database = FirebaseDatabase.getInstance();
        myRefYemekler = database.getReference("yemekler");

        tb2.setTitle(kategoriler.getKategori_ad());
        setSupportActionBar(tb2);




        yemek_rv.setHasFixedSize(true);
        yemek_rv.setLayoutManager(new LinearLayoutManager(this));

        yemeklerArrayList=new ArrayList<>();

        adapter=new YemeklerAdapter(yemeklerArrayList,this);
        yemek_rv.setAdapter(adapter);

        yemekGetirByKategori();





    }



    public void yemekGetirByKategori(){

        Query sorgu=myRefYemekler.orderByChild("kategori_ad").equalTo(kategoriler.getKategori_ad());

        sorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                yemeklerArrayList.clear();

                for (DataSnapshot s3: snapshot.getChildren()){
                    Yemekler yemekler=s3.getValue(Yemekler.class);

                    yemeklerArrayList.add(yemekler);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void arama(final String aramaKelime){


        myRefYemekler.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                yemeklerArrayList.clear();

                for (DataSnapshot s3: snapshot.getChildren()){
                    Yemekler yemekler=s3.getValue(Yemekler.class);
                    if(yemekler.getYemek_ad().contains(aramaKelime)) {
                        yemekler.setYemek_id(s3.getKey());
                        yemeklerArrayList.add(yemekler);
                    }

                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}