package com.inkubator.adryan.learnarabic.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.config.ServerConfig;
import com.inkubator.adryan.learnarabic.database.DbHelper;
import com.inkubator.adryan.learnarabic.model.MateriDetail;
import com.inkubator.adryan.learnarabic.utils.AudioPlay;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

/**
 * Created by adryanev on 05/10/17.
 */

public class MateriDetailAdapter extends RecyclerView.Adapter<MateriDetailAdapter.MateriDetailViewHolder> {

    private DbHelper db;
    private List<MateriDetail> materiDetailsList;
    private Context context;

    MediaPlayer mp;

    public static class MateriDetailViewHolder extends RecyclerView.ViewHolder {
        LinearLayout materiDetailLayout;
        ImageView gambar;
        TextView arab;
        TextView terjemahan;
        ImageButton im;

        public MateriDetailViewHolder(View itemView) {
            super(itemView);
            materiDetailLayout = (LinearLayout) itemView.findViewById(R.id.materi_layout);
            gambar = (ImageView) itemView.findViewById(R.id.iv_materi);
            arab = (TextView) itemView.findViewById(R.id.tv_arab);
            terjemahan = (TextView) itemView.findViewById(R.id.tv_terjemahan);
            im = (ImageButton) itemView.findViewById(R.id.sound);

            
        }
    }

    public MateriDetailAdapter(List<MateriDetail> materiDetails, Context context, MediaPlayer mp){
        db = new DbHelper(context);
        this.mp = mp;
        this.materiDetailsList = materiDetails;
        this.context = context;
    }

    @Override
    public MateriDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.materi_detail_card_view,parent,false);
        Log.d("MateriDetailAdapter", "Sukses Meload Materi Detail CardView");
        return new MateriDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MateriDetailViewHolder holder, int position) {
        final MateriDetail materiDetail = materiDetailsList.get(position);
        Picasso.with(context).load(ServerConfig.IMAGE_FOLDER+materiDetail.getGambar()).into(holder.gambar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.arab.setText(Html.fromHtml(materiDetail.getIsi(),Html.FROM_HTML_MODE_LEGACY));

        }else{
            holder.arab.setText(Html.fromHtml(materiDetail.getIsi()));
        }

        holder.arab.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
        Integer idKategori = db.getIdKategoriFromSubMateri(materiDetail.getIdSubMateri());
        switch (idKategori){
            case 3:
            holder.arab.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            holder.arab.setTextSize(TypedValue.COMPLEX_UNIT_PT,11);

        }

        if(materiDetailsList.get(position).getSuara()!=null){
            holder.im.setVisibility(View.VISIBLE);
        }

        holder.im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AudioPlay.playAudio(context,ServerConfig.SUARA_FOLDER+materiDetail.getSuara());
                /*
                try {
                    mp.release();
                    mp = new MediaPlayer();
                    mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mp.setDataSource(ServerConfig.SUARA_FOLDER+materiDetail.getSuara());
                    mp.prepareAsync();
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            if(mp.isPlaying()){ mp.pause(); mp.release();}
                            else{mp.start();}
                        }
                    });
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            mp = null;
                        }

                    });


                } catch (IOException e) {
                    Toast.makeText(context,"Tidak menemukan file mp3 di server.",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                */
            }

        });


    }

    @Override
    public int getItemCount() {
        return materiDetailsList.size();
    }
}
