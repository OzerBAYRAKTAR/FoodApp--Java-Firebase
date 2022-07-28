package com.abc.tarifkitabim.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abc.tarifkitabim.Classes.EklemeClass;
import com.abc.tarifkitabim.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EklemeDetayActivity extends AppCompatActivity {

    private TextView baslıktext, malzemetext, yapilistxt;
    private ImageView geributton;
    private EklemeClass eklemeClass;
    private Context mContext;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekleme_detay);

        baslıktext=findViewById(R.id.baslıktext);
        malzemetext=findViewById(R.id.malzemetext);
        yapilistxt=findViewById(R.id.yapilistxt);
        geributton=findViewById(R.id.geributton);





        eklemeClass=(EklemeClass) getIntent().getSerializableExtra("nesne");



        baslıktext.setText(String.valueOf(eklemeClass.getTarif_ad()));
        malzemetext.setText(String.valueOf(eklemeClass.getTarif_malzeme()));
        yapilistxt.setText(String.valueOf(eklemeClass.getTarif_yapilis()));

        geributton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}