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

import com.abc.tarifkitabim.Activities.DetayPopular;
import com.abc.tarifkitabim.Classes.Popular;
import com.abc.tarifkitabim.R;
import com.bumptech.glide.Glide;


import java.util.List;

public class PopulerAdapter extends  RecyclerView.Adapter<PopulerAdapter.cardTutucu>{
    private List<Popular> popularArrayList;
    private Context mContext;



    public PopulerAdapter(List<Popular> popularArrayList, Context mContext) {
        this.popularArrayList = popularArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PopulerAdapter.cardTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.populer_card_tasarim,parent,false);
        return new cardTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cardTutucu holder, int position) {

        final Popular popular= popularArrayList.get(position);
        holder.tvPopulerAd.setText(popular.getPopular_ad());

        Glide.with(mContext).load(popularArrayList.get(position).getPopular_resim()).centerCrop()
                .into(holder.ivPopulerResim);

        holder.cardViewPopuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext, DetayPopular.class);
                i.putExtra("popularnesne",popular);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return popularArrayList.size();
    }

    public class cardTutucu extends RecyclerView.ViewHolder{
        private CardView cardViewPopuler;
        private TextView tvPopulerAd;
        private ImageView ivPopulerResim;

        public cardTutucu(@NonNull View itemView) {
            super(itemView);

            cardViewPopuler=itemView.findViewById(R.id.cardViewPopuler);
            tvPopulerAd=itemView.findViewById(R.id.tvPopulerAd);
            ivPopulerResim=itemView.findViewById(R.id.ivPopulerResim);
        }
    }
}