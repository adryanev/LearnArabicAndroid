package com.inkubator.adryan.learnarabic.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.adapter.VideoAdapter;
import com.inkubator.adryan.learnarabic.model.Video;
import com.inkubator.adryan.learnarabic.response.ResponseUjian;
import com.inkubator.adryan.learnarabic.response.ResponseVideo;
import com.inkubator.adryan.learnarabic.rest.ApiClient;
import com.inkubator.adryan.learnarabic.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVideo extends Fragment {

    ProgressDialog pd;
    List<Video> videoList;
    RecyclerView rv;
    private  static final String TAG = FragmentVideo.class.getSimpleName();
    public FragmentVideo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        getActivity().setTitle("Video");
        rv= (RecyclerView) view.findViewById(R.id.rv_video);
        prepareVideo();

        return view;
    }

    private void prepareVideo() {
        pd = ProgressDialog.show(getActivity(),null,"Sedang mendapatkan video.",false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseVideo> call = apiService.getVideo();
        call.enqueue(new Callback<ResponseVideo>() {
            @Override
            public void onResponse(Call<ResponseVideo> call, Response<ResponseVideo> response) {
                if(response.isSuccessful()){
                    List<Video> vd = new ArrayList<>();
                    pd.dismiss();
                    Log.d(TAG,"Sukses Mendapatkan List Video dari server");
                    vd = response.body().getVideo();
                    rv.setLayoutManager(new GridLayoutManager(getContext(),2));
                    rv.setAdapter(new VideoAdapter(getContext(),vd));
                }
            }

            @Override
            public void onFailure(Call<ResponseVideo> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
