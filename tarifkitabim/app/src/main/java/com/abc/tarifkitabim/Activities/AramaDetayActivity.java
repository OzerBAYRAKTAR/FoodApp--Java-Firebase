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

public class AramaDetayActivity extends AppCompatActivity {

    private TextView txtmalzeme, txtyapilis, txtbaslik;
    private ImageView Yemekiv;
    private ImageButton imBttn;
    private Yemekler yemek;
    private Context mContext;
    private FirebaseDatabase database;
    private DatabaseReference myRefarama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama_detay);

        txtmalzeme = findViewById(R.id.txtmalzeme);
        txtyapilis = findViewById(R.id.txtyapilis);
        txtbaslik = findViewById(R.id.txtbaslik);
        Yemekiv = findViewById(R.id.Yemekiv);
        imBttn = findViewById(R.id.imBttn);



        imBttn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });


         yemek = (Yemekler) getIntent().getSerializableExtra("yemekler");



        txtbaslik.setText(yemek.getYemek_ad());
        txtyapilis.setText(yemek.getYapilis_text());
        txtmalzeme.setText(yemek.getMalzeme_text());



        database = FirebaseDatabase.getInstance();
        myRefarama = database.getReference("yemekler");


    getResim();

}

    public void getResim() {
        Query myrefSorgu = myRefarama.orderByChild("yemek_resim").equalTo(yemek.getYemek_resim());
        myrefSorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ss : snapshot.getChildren()) {

                    Glide.with(AramaDetayActivity.this).load(ss.child("yemek_resim").getValue(String.class)).into(Yemekiv);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}