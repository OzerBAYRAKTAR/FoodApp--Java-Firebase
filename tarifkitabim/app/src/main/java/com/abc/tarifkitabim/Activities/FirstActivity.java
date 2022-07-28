package com.abc.tarifkitabim.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import com.abc.tarifkitabim.Adapters.PopulerAdapter;
import com.abc.tarifkitabim.Adapters.YemeklerAdapter;
import com.abc.tarifkitabim.Adapters.KategorilerAdapter;

import com.abc.tarifkitabim.Classes.Kategoriler;
import com.abc.tarifkitabim.Classes.Popular;
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

public class FirstActivity extends AppCompatActivity {

    private Context mContext;
    private ArrayList<Kategoriler> kategorilerArrayList;
    private ArrayList<Popular> popularArrayList;
    private ArrayList<Yemekler> yemekList;
    private Button btnArama;
    private KategorilerAdapter kategorileradapter;
    private YemeklerAdapter yemeklerAdapter;
    private RecyclerView kategori_rv,populer_rv;
    private PopulerAdapter populerAdapter;
    private LinearLayout anasayfaBtn,profilBtn,ayarlarBtn;
    private SharedPreferences sp;
    private HesapActivity hesapActivity;
    private SharedPreferences.Editor editor;
    private FloatingActionButton fabGoEkleme;
    private String ad;
    FirebaseDatabase database;
    DatabaseReference myRefkategoriler,myRefpopular,myrefYemekler;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        anasayfaBtn=findViewById(R.id.anasayfaBtn);
        profilBtn=findViewById(R.id.profilBtn);
        fabGoEkleme=findViewById(R.id.fabGoEkleme);
        btnArama=findViewById(R.id.btnArama);

        btnArama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this,AramaActivitySayfasi.class));
            }
        });







        fabGoEkleme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this,EkleActivity.class));

            }
        });




        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp=getSharedPreferences("girisbilgi",MODE_PRIVATE);
                editor=sp.edit();
                ad=sp.getString("ad","");

                if(ad.contains("")){
                    startActivity(new Intent(FirstActivity.this, HesapActivity.class));

                }else {
                    startActivity(new Intent(FirstActivity.this,FirstActivity.class));
               }

            }
        });
        anasayfaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());

            }
        });






        populer_rv = findViewById(R.id.populer_rv);
        populer_rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        populer_rv.setHasFixedSize(true);

        //firebase
        database = FirebaseDatabase.getInstance();
        myRefpopular = database.getReference();
        popularArrayList = new ArrayList<>();


        clearPopular();
        getPopular();




        //PART OF KATEGORİ
        kategori_rv = findViewById(R.id.kategori_rv);
        kategori_rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        kategori_rv.setHasFixedSize(true);

        //firebase
        database = FirebaseDatabase.getInstance();
        myRefkategoriler = database.getReference();



        kategorilerArrayList = new ArrayList<>();

        clearAll();
        getDataFromFirebase();





    }


    public void clearAll(){
        if(kategorilerArrayList !=null){
            kategorilerArrayList.clear();

            if(kategorileradapter !=null){
                kategorileradapter.notifyDataSetChanged();
            }
        }
            kategorilerArrayList=new ArrayList<>();
        }


    public void getDataFromFirebase(){


        Query query=myRefkategoriler.child("kategoriler");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s1: snapshot.getChildren()){
                    Kategoriler kategoriler=new Kategoriler();

                    kategoriler.setKategori_resim(s1.child("kategori_resim").getValue().toString());
                    kategoriler.setKategori_ad(s1.child("kategori_ad").getValue().toString());

                    kategorilerArrayList.add(kategoriler);

                }
                kategorileradapter=new KategorilerAdapter(getApplicationContext(),kategorilerArrayList);
                kategori_rv.setAdapter(kategorileradapter);
                kategorileradapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getPopular(){


            Query query=myRefpopular.child("popular");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    popularArrayList.clear();
                    for(DataSnapshot s2: snapshot.getChildren()){
                        Popular popular=new Popular();

                        popular.setPopular_resim(s2.child("popular_resim").getValue().toString());
                        popular.setPopular_ad(s2.child("popular_ad").getValue().toString());

                        popularArrayList.add(popular);
                    }
                    populerAdapter=new PopulerAdapter(popularArrayList,getApplicationContext());
                    populer_rv.setAdapter(populerAdapter);
                    populerAdapter.notifyDataSetChanged();



                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    public void clearPopular(){
        if(popularArrayList !=null){
            popularArrayList.clear();

            if(populerAdapter !=null){
                populerAdapter.notifyDataSetChanged();
            }
        }
        popularArrayList=new ArrayList<>();
    }





}




