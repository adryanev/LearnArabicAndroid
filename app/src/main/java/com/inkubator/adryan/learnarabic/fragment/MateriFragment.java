package com.inkubator.adryan.learnarabic.fragment;

import android.content.Intent;
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
import com.inkubator.adryan.learnarabic.activity.MateriDetailActivity;
import com.inkubator.adryan.learnarabic.adapter.MateriAdapter;
import com.inkubator.adryan.learnarabic.adapter.MateriDetailAdapter;
import com.inkubator.adryan.learnarabic.model.Materi;
import com.inkubator.adryan.learnarabic.model.MateriDetail;
import com.inkubator.adryan.learnarabic.response.ResponseMateri;
import com.inkubator.adryan.learnarabic.rest.ApiClient;
import com.inkubator.adryan.learnarabic.rest.ApiInterface;
import com.inkubator.adryan.learnarabic.response.ResponseMateriDetail;

import java.util.List;

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

        View view = inflater.inflate(R.layout.fragment_materi,container,false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseMateri> call = apiService.getMateri();
        call.enqueue(new Callback<ResponseMateri>() {
            @Override
            public void onResponse(Call<ResponseMateri> call, Response<ResponseMateri> response) {

                if(response.isSuccessful()){
                    List<Materi> materi = response.body().getMateri();


                    recyclerView.setAdapter(new MateriAdapter(materi,getContext(), new MateriAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Materi materi) {
                            Intent intent = new Intent(getActivity(), MateriDetailActivity.class);
                            intent.putExtra("idMateri",materi.getIdMateri());
                            startActivity(intent);
                        }
                    }){

                    });
                    Log.d(TAG,"Succes receiving: "+materi.size());
                }
                else{
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.d(TAG,"Gagal, Error Code ="+statusCode);
                }

            }

            @Override
            public void onFailure(Call<ResponseMateri> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
        return view;
    }
}
