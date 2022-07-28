package com.abc.tarifkitabim.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.abc.tarifkitabim.Activities.DetayActivity;
import com.abc.tarifkitabim.Classes.Yemekler;
import com.abc.tarifkitabim.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.List;

public class YemeklerAdapter extends  RecyclerView.Adapter<YemeklerAdapter.cardTasarimTutucu>{
    private List<Yemekler> yemeklerArrayList;
    private Context mContext;


    public void setYemeklerArrayList(List<Yemekler> yemeklerArrayList) {
        this.yemeklerArrayList = yemeklerArrayList;
    }

    public YemeklerAdapter(List<Yemekler> yemeklerArrayList, Context mContext) {
        this.yemeklerArrayList = yemeklerArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public cardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.yemekler_card_tasarim,parent,false);
        return new cardTasarimTutucu(view);
    }



    @Override
    public void onBindViewHolder(@NonNull cardTasarimTutucu holder, int position) {



        final Yemekler yemekler= yemeklerArrayList.get(position);
        holder.textViewYemekAd.setText(yemekler.getYemek_ad());
        holder.textViewSure.setText(yemekler.getSure_dk());
        holder.textViewKisi.setText(yemekler.getKackisi_adet());
        Glide.with(mContext).load(yemeklerArrayList.get(position).getYemek_resim()).centerCrop()
                .into(holder.imageViewYemek);




        holder.cardViewYemek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext.getApplicationContext(), DetayActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("yemekNesne",yemekler);
                mContext.startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() {
        return yemeklerArrayList.size();
    }


    public class cardTasarimTutucu extends RecyclerView.ViewHolder {

        private TextView textViewYemekAd,textViewKisi,textViewSure;
        private ImageView imageViewYemek;
        private CardView cardViewYemek;


        public cardTasarimTutucu(@NonNull View itemView) {

            super(itemView);

            textViewYemekAd=itemView.findViewById(R.id.textViewYemekAd);
            textViewKisi=itemView.findViewById(R.id.textViewKisi);
            textViewSure=itemView.findViewById(R.id.textViewSure);
            imageViewYemek=itemView.findViewById(R.id.ivYemek);
            cardViewYemek=itemView.findViewById(R.id.cardViewYemek);

        }
    }
}
