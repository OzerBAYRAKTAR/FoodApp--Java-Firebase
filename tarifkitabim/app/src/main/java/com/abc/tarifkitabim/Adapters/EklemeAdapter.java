package com.abc.tarifkitabim.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.abc.tarifkitabim.Activities.EklemeDetayActivity;
import com.abc.tarifkitabim.Classes.EklemeClass;
import com.abc.tarifkitabim.Classes.tarifEklemeDao;
import com.abc.tarifkitabim.veritabaniTarifEkleme.dataBase;

import com.abc.tarifkitabim.R;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;


public class EklemeAdapter extends RecyclerView.Adapter<EklemeAdapter.CardTasarimTutucu>{

    private Context mContext;
    private List<EklemeClass> eklemeClassList;
    private dataBase vt;


    public EklemeAdapter(Context mContext, List<EklemeClass> eklemeClassList, dataBase vt) {
        this.mContext = mContext;
        this.eklemeClassList = eklemeClassList;
        this.vt = vt;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ekleme_card_tasarim,parent,false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {

        EklemeClass eklemeClass=eklemeClassList.get(position);

        holder.txtAd.setText(eklemeClass.getTarif_ad());

        holder.ivSecenek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popmenu=new PopupMenu(v.getContext(), holder.ivSecenek);
                popmenu.getMenuInflater().inflate(R.menu.popupp_menu,popmenu.getMenu());


                popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_guncelle:
                                alrtgstr(eklemeClass);
                                return true;
                            case R.id.action_sil:
                                Snackbar.make(holder.ivSecenek," TARİF SİLİNSİN Mİ ?",Snackbar.LENGTH_SHORT).
                                        setAction("EVET", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                new tarifEklemeDao().tarifSil(vt,eklemeClass.getTarif_id());
                                                eklemeClassList=new tarifEklemeDao().tumTarifler(vt);
                                                notifyDataSetChanged();
                                            }
                                        }).show();
                                return  true;
                            default:
                                return false;
                        }

                    }
                });
                popmenu.show();
            }
        });
        holder.cvEkleme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, EklemeDetayActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nesne",eklemeClass);
                mContext.startActivity(intent);
            }
        });


    }

    private void alrtgstr(EklemeClass eklemeClass) {

        LayoutInflater lai=LayoutInflater.from(mContext);
        View tasarim=lai.inflate(R.layout.alert_tasarimm,null);

        final EditText editTextAd=tasarim.findViewById(R.id.editTextAd);
        final TextView tvMalz=tasarim.findViewById(R.id.tvMalz);
        final TextView tvYap=tasarim.findViewById(R.id.tvYap);



        AlertDialog.Builder ad=new AlertDialog.Builder(mContext);
        ad.setTitle("KİŞİ DÜZENLE");
        ad.setView(tasarim);


        ad.setPositiveButton("DÜZENLE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String tarif_ad=editTextAd.getText().toString().trim();
                String tarif_malzeme=tvMalz.getText().toString().trim();
                String tarif_yapilis=tvYap.getText().toString().trim();



                if (!editTextAd.getText().toString().equals("") && !tvMalz.getText().toString().equals("") && !tvYap.getText().toString().equals("") ){

                    new tarifEklemeDao().tarifguncelle(vt,eklemeClass.getTarif_id(),tarif_ad,tarif_malzeme,tarif_yapilis);
                    eklemeClassList=new tarifEklemeDao().tumTarifler(vt);
                    notifyDataSetChanged();

                }else{
                    Toast.makeText(mContext, "GÜNCELLEME YAPILAMADI! LÜTFEN GEREKLİ ALANLARI DOLDURUN.", Toast.LENGTH_SHORT).show();


                }
                notifyDataSetChanged();

            }
        });
        ad.setNegativeButton("İPTAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        ad.create().show();

    }

    @Override
    public int getItemCount() {
        return eklemeClassList.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
    private TextView txtAd;
    private ImageView ivSecenek;
    private CardView cvEkleme;


    public CardTasarimTutucu(@NonNull View itemView) {
        super(itemView);

        txtAd=itemView.findViewById(R.id.txtAd);
        ivSecenek=itemView.findViewById(R.id.ivSecenek);
        cvEkleme=itemView.findViewById(R.id.cvEkleme);


    }

}













}
