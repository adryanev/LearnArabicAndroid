package com.inkubator.adryan.learnarabic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.config.ServerConfig;
import com.inkubator.adryan.learnarabic.model.MateriDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by adryanev on 05/10/17.
 */

public class MateriDetailAdapter extends RecyclerView.Adapter<MateriDetailAdapter.MateriDetailViewHolder> {

    private List<MateriDetail> materiDetailsList;
    private Context context;

    public static class MateriDetailViewHolder extends RecyclerView.ViewHolder {
        LinearLayout materiDetailLayout;
        ImageView gambar;
        TextView arab;
        TextView terjemahan;
        public MateriDetailViewHolder(View itemView) {
            super(itemView);
            materiDetailLayout = (LinearLayout) itemView.findViewById(R.id.materi_layout);
            gambar = (ImageView) itemView.findViewById(R.id.iv_materi);
            arab = (TextView) itemView.findViewById(R.id.tv_arab);
            terjemahan = (TextView) itemView.findViewById(R.id.tv_terjemahan);
        }
    }

    public MateriDetailAdapter(List<MateriDetail> materiDetails, Context context){

        this.materiDetailsList = materiDetails;
        this.context = context;
    }

    @Override
    public MateriDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.materi_detail_card_view,parent,false);
        return new MateriDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MateriDetailViewHolder holder, int position) {
        MateriDetail materiDetail = materiDetailsList.get(position);
        Picasso.with(context).load(ServerConfig.IMAGE_FOLDER+materiDetail.getGambar()).resize(300,300).centerCrop().into(holder.gambar);
        holder.arab.setText(Html.fromHtml(materiDetail.getIsi()));
        switch (materiDetail.getIdSubMateri()){
            case 1:case 3:
            holder.arab.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
        }
        if(materiDetail.getTerjemahan()!= null && !materiDetail.getTerjemahan().isEmpty()){
            holder.terjemahan.setText(Html.fromHtml(materiDetail.getTerjemahan()));
        }

    }

    @Override
    public int getItemCount() {
        return materiDetailsList.size();
    }
}
