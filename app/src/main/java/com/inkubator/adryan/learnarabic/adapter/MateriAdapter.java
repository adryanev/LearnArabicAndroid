package com.inkubator.adryan.learnarabic.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.model.Materi;

import java.util.List;

/**
 * Created by Adryan Eka Vandra on 10/6/2017.
 */

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.MateriViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(Materi materi);
    }
    private OnItemClickListener onItemClickListener;
    private List<Materi> materi;
    private Context context;

    public static class MateriViewHolder extends RecyclerView.ViewHolder{
        LinearLayout materiLayout;
        TextView namaMateri;

        public MateriViewHolder(View view){
            super(view);
            materiLayout = (LinearLayout) view.findViewById(R.id.materi_layout);
            namaMateri = (TextView) view.findViewById(R.id.tv_materi);


        }
        public void bind(final Materi materi, final OnItemClickListener listener){

            namaMateri.setText(materi.getNamaMateri());
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    listener.onItemClick(materi);
                }
            });
        }
    }

    public MateriAdapter(List<Materi> materi, Context context,OnItemClickListener listener ){
        this.materi = materi;
        this.context = context;
        this.onItemClickListener = listener;
    }

    @Override
    public MateriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.materi_card_view,parent,false);
        return new MateriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MateriViewHolder holder, int position) {

        holder.bind(materi.get(position),onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return materi.size();
    }
}
