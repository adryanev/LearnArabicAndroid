package com.inkubator.adryan.learnarabic.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                if(checkConnectivity(getContext()) == true){
                   startActivity(i);
                }else{
                    Toast.makeText(getContext(),"Silahkan Hidupkan Wifi/Mobile Data anda.",Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    public boolean checkConnectivity(Context c){
        ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo !=null && networkInfo.isConnected()) return true;
        else return false;

    }

}
