package com.abc.tarifkitabim.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.abc.tarifkitabim.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HesapActivity extends AppCompatActivity {
    TextView tvAd,tvSoyad,tvYas,tvGelenAd;
    private FloatingActionButton fabHome,fabAdd;
    private ImageView ivFoto;
    private Toolbar toolbar;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    String ad,soy,yas,alertAd,alertSoyad,alertYas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hesap);


        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("PROFİL");
        setSupportActionBar(toolbar);

        tvAd=findViewById(R.id.tvAd);
        tvSoyad=findViewById(R.id.tvSoyad);
        tvYas=findViewById(R.id.tvYas);

        tvGelenAd=findViewById(R.id.tvGelenAd);
        fabHome=findViewById(R.id.fabHome);
        ivFoto=findViewById(R.id.ivFoto);
        fabAdd=findViewById(R.id.fabAdd);



        sp=getSharedPreferences("girisbilgi",0);
        editor=sp.edit();

        ivFoto=findViewById(R.id.ivFoto);


        ad=sp.getString("ad","");
        soy=sp.getString("soy","");
        yas=sp.getString("yas","");

        tvAd.setText("Ad: "+ad);
        tvSoyad.setText("Soyad: "+soy);
        tvYas.setText("Yaş : "+yas);
        tvGelenAd.setText(ad);







        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HesapActivity.this,FirstActivity.class));


            }
        });
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String girilenad=sp.getString("ad","");

                if(tvAd.getText().toString().equals("") && tvAd.getText().length()<=0){
                    Toast.makeText(HesapActivity.this, "zaten kayıt yapıldı", Toast.LENGTH_SHORT).show();

                }else {
                    AlertGoster();

                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_cikis:
                editor.clear();
                editor.commit();
                startActivity(new Intent(HesapActivity.this,FirstActivity.class));
                Toast.makeText(this, "HESAP SİLİNDİ", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void AlertGoster(){
        LayoutInflater layout=LayoutInflater.from(this);
        View tasarim=layout.inflate(R.layout.alert_tasarim,null);

        EditText alertAd=tasarim.findViewById(R.id.alertAd);
        EditText alertSoyad=tasarim.findViewById(R.id.alertSoyad);
        EditText alertYas=tasarim.findViewById(R.id.alertYas);


        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        ad.setTitle("Hesap Oluşturma");
        ad.setView(tasarim);

        ad.setPositiveButton("Oluştur", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                editor.putString("ad",alertAd.getText().toString().trim());
                editor.putString("soy",alertSoyad.getText().toString().trim());
                editor.putString("yas",alertYas.getText().toString().trim());
                editor.commit();
                if(!alertAd.getText().toString().equals("")&& !alertSoyad.getText().toString().equals("") && !alertYas.getText().toString().equals("")){
                    Toast.makeText(HesapActivity.this, "KAYIT YAPILDI", Toast.LENGTH_SHORT).show();
                    HesapActivity.this.startActivity(new Intent(HesapActivity.this,FirstActivity.class));
                }else{
                    Toast.makeText(HesapActivity.this, "TÜM BOŞLUKLARI DOLDURUN", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ad.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        ad.create().show();



    }
}