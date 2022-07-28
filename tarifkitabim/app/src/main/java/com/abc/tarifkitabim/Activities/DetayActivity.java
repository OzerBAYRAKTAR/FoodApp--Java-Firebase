package com.abc.tarifkitabim.Activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.abc.tarifkitabim.Classes.Yemekler;
import com.abc.tarifkitabim.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class DetayActivity extends AppCompatActivity {
    private TextView textMalzemeler, textYapilis, textViewBaslik;
    private ImageView imageViewYemek;
    private ImageButton imageButton3;
    private Yemekler yemekler;
    private Context mContext;
    private FirebaseDatabase database;
    private DatabaseReference myrefRes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);


        textMalzemeler = findViewById(R.id.txmalz);
        textYapilis = findViewById(R.id.txyapilis);
        textViewBaslik = findViewById(R.id.textViewBaslik);
        imageViewYemek = findViewById(R.id.ivYemek);
        imageButton3 = findViewById(R.id.imGeri);



        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        yemekler = (Yemekler) getIntent().getSerializableExtra("yemekNesne");



        textViewBaslik.setText(yemekler.getYemek_ad());
        textYapilis.setText(yemekler.getYapilis_text());
        textMalzemeler.setText(yemekler.getMalzeme_text());



        database = FirebaseDatabase.getInstance();
        myrefRes = database.getReference("yemekler");


        getResim();

    }

    public void getResim() {
        Query myrefResSor = myrefRes.orderByChild("yemek_resim").equalTo(yemekler.getYemek_resim());
        myrefResSor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss : snapshot.getChildren()) {

                    Glide.with(DetayActivity.this).load(dss.child("yemek_resim").getValue(String.class)).into(imageViewYemek);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}


