package com.abc.tarifkitabim.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.abc.tarifkitabim.Adapters.AramaAdapter;
import com.abc.tarifkitabim.Classes.Yemekler;
import com.abc.tarifkitabim.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AramaActivitySayfasi extends AppCompatActivity {
    private RecyclerView arama_rv;
    private SearchView svArama;
    private Yemekler yemekler;
    private FloatingActionButton homeFab;
    private AramaAdapter aramaAdapter;
    private ArrayList<Yemekler> yemekArrayList;
    private FirebaseDatabase database;
    private DatabaseReference myref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama_sayfasi);

        arama_rv=findViewById(R.id.arama_rv);
        svArama=findViewById(R.id.svArama);
        homeFab=findViewById(R.id.homeFab);

        homeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AramaActivitySayfasi.this,FirstActivity.class));
            }
        });

        database = FirebaseDatabase.getInstance();
        myref = database.getReference("yemekler");

        arama_rv.setHasFixedSize(true);
        arama_rv.setLayoutManager(new LinearLayoutManager(this));

        yemekArrayList=new ArrayList<>();
        aramaAdapter=new AramaAdapter(this,yemekArrayList);
        arama_rv.setAdapter(aramaAdapter);

        tumYemekler();

        svArama.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                AramaActivitySayfasi.this.search(newText);
                return false;
            }
        });





    }

    private void tumYemekler() {

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                yemekArrayList.clear();

                for(DataSnapshot ds:snapshot.getChildren()){
                    Yemekler yemekler=ds.getValue(Yemekler.class);
                    yemekArrayList.add(yemekler);

                }
                aramaAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void search(final String searchWord) {
        this.myref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {

                AramaActivitySayfasi.this.yemekArrayList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Yemekler yemekler = (Yemekler) ds.getValue(Yemekler.class);
                    if (yemekler.getYemek_ad().toString().toLowerCase().contains(searchWord.toString().toLowerCase())) {
                        yemekler.setYemek_id(ds.getKey());
                        AramaActivitySayfasi.this.yemekArrayList.add(yemekler);

                    }
                }
                AramaActivitySayfasi.this.aramaAdapter.notifyDataSetChanged();
            }

            public void onCancelled(DatabaseError error) {
            }
        });
    }


}