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


import com.abc.tarifkitabim.Activities.YemeklerActivity;
import com.abc.tarifkitabim.Classes.Kategoriler;

import com.abc.tarifkitabim.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class KategorilerAdapter extends RecyclerView.Adapter<KategorilerAdapter.cardViewTutucu> {


    private Context mContext;
    private List<Kategoriler> kategorilerArrayList;

    public KategorilerAdapter(Context mContext, List<Kategoriler> kategorilerList) {
        this.mContext = mContext;
        this.kategorilerArrayList = kategorilerList;
    }

    @NonNull
    @Override
    public KategorilerAdapter.cardViewTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.kategoriler_card_tasarim,parent,false);
        return new cardViewTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cardViewTutucu holder, int position) {

        final Kategoriler kategoriler=kategorilerArrayList.get(position);
        holder.tvYemekAd.setText(kategoriler.getKategori_ad());

        Glide.with(mContext).load(kategorilerArrayList.get(position).getKategori_resim()).centerCrop()
                .into(holder.ivYemekResim);


        holder.cardViewKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, YemeklerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("kategoriNesne",kategoriler);
                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return kategorilerArrayList.size();
    }

    public class cardViewTutucu extends RecyclerView.ViewHolder{
        private CardView cardViewKategori;
        private ImageView ivYemekResim;
        private TextView tvYemekAd;


        public cardViewTutucu(@NonNull View itemView) {
            super(itemView);
            cardViewKategori=itemView.findViewById(R.id.cardViewKategori);
            ivYemekResim=itemView.findViewById(R.id.ivYemekResim);
            tvYemekAd=itemView.findViewById(R.id.tvYemekAd);

        }
    }
}
