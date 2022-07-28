package com.abc.tarifkitabim.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.abc.tarifkitabim.Classes.Popular;
import com.abc.tarifkitabim.Classes.Yemekler;
import com.abc.tarifkitabim.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DetayPopular extends AppCompatActivity {
    private TextView tvBaslik, txtMalzemeler, txtYapilis;
    private ImageButton imButton;
    private ImageView ivYemek;
    private Popular popular;
    private Yemekler yemekler;
    private FirebaseDatabase database;
    private DatabaseReference myref;


    private Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaytwo);


        tvBaslik = findViewById(R.id.textViewBaslik);
        txtMalzemeler = findViewById(R.id.txmalz);
        txtYapilis = findViewById(R.id.txyapilis);
        imButton = findViewById(R.id.imGeri);
        imButton = findViewById(R.id.imGeri);
        ivYemek = findViewById(R.id.ivYemek);


        popular=(Popular) getIntent().getSerializableExtra("popularnesne");


        database = FirebaseDatabase.getInstance();
        myref = database.getReference("yemekler");




        imButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetayPopular.this, FirstActivity.class);
                finish();
                startActivity(i);
            }
        });

        getData();





    }
    private void getData() {
        Query myrefsorgu=myref.orderByChild("yemek_ad").equalTo(popular.getPopular_ad());

        myrefsorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){

                    txtMalzemeler.setText( ds.child("malzeme_text").getValue(String.class));
                    txtYapilis.setText( ds.child("yapilis_text").getValue(String.class));
                    tvBaslik.setText( ds.child("yemek_ad").getValue(String.class));

                    Glide.with(DetayPopular.this).load(ds.child("yemek_resim").getValue(String.class)).into(ivYemek);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





}



