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

import com.abc.tarifkitabim.Activities.AramaDetayActivity;
import com.abc.tarifkitabim.Activities.DetayActivity;
import com.abc.tarifkitabim.Classes.Yemekler;
import com.abc.tarifkitabim.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AramaAdapter extends RecyclerView.Adapter<AramaAdapter.cardtutucu>{

    private Context mContext;
    private List<Yemekler> yemekArrayList;

    public AramaAdapter(Context mContext, List<Yemekler> yemekArrayList) {
        this.mContext = mContext;
        this.yemekArrayList = yemekArrayList;
    }

    @NonNull
    @Override
    public cardtutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.arama_card_tasarim,parent,false);
        return new cardtutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cardtutucu holder, int position) {

        final Yemekler yemek= yemekArrayList.get(position);
        holder.adtv.setText(yemek.getYemek_ad());
        holder.kisitv.setText(yemek.getSure_dk());
        holder.dktv.setText(yemek.getKackisi_adet());
        Glide.with(mContext).load(yemekArrayList.get(position).getYemek_resim()).centerCrop()
                .into(holder.yemekiv);

        holder.cardAramaYemek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext.getApplicationContext(), AramaDetayActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("yemekler",yemek);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return yemekArrayList.size();
    }

    public class cardtutucu extends RecyclerView.ViewHolder{
        private TextView adtv,kisitv,dktv;
        private ImageView yemekiv;
        private CardView cardAramaYemek;


        public cardtutucu(@NonNull View itemView) {
            super(itemView);
            adtv=itemView.findViewById(R.id.adtv);
            kisitv=itemView.findViewById(R.id.kisitv);
            dktv=itemView.findViewById(R.id.dktv);
            yemekiv=itemView.findViewById(R.id.yemekiv);
            cardAramaYemek=itemView.findViewById(R.id.cardAramaYemek);
        }
    }
}
