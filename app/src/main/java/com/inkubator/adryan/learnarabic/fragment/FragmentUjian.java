package com.inkubator.adryan.learnarabic.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.activity.UjianActivity;

/**
 * Created by Adryan Eka Vandra on 10/6/2017.
 */

public class FragmentUjian extends Fragment {

    TextView tvInfo;
    Button btnUjian;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ujian,container,false);
        final Intent i = new Intent(getActivity(),UjianActivity.class);
        btnUjian = (Button) view.findViewById(R.id.btn_kerjakan);
        btnUjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(v.getContext())
                        .setTitle("Mulai Ujian")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(i);
                            }
                        }
                        ).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).setMessage("Semoga Sukses");
                Intent intent = new Intent(getContext(), UjianActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
