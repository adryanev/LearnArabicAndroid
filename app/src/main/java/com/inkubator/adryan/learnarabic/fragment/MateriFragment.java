package com.inkubator.adryan.learnarabic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.activity.MainActivity;
import com.inkubator.adryan.learnarabic.adapter.MateriAdapter;
import com.inkubator.adryan.learnarabic.model.Materi;
import com.inkubator.adryan.learnarabic.model.MateriDetail;
import com.inkubator.adryan.learnarabic.rest.ApiClient;
import com.inkubator.adryan.learnarabic.rest.ApiInterface;
import com.inkubator.adryan.learnarabic.rest.ResponseMateriDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adryanev on 05/10/17.
 */

public class MateriFragment extends Fragment{

    private  static final String TAG = MainActivity.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main,container,false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseMateriDetail> call = apiService.getMateriDetails();
        call.enqueue(new Callback<ResponseMateriDetail>() {
            @Override
            public void onResponse(Call<ResponseMateriDetail> call, Response<ResponseMateriDetail> response) {

                if(response.isSuccessful()){
                    List<MateriDetail> materiDetails = response.body().getMateriDetails();
                    Log.d(TAG,"Succes receiving: "+materiDetails.size());

                    recyclerView.setAdapter(new MateriAdapter(materiDetails,getContext()));
                }
                else{
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.d(TAG,"Gagal, Error Code ="+statusCode);
                }

            }

            @Override
            public void onFailure(Call<ResponseMateriDetail> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
        return view;
    }
}
