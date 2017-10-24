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
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.activity.MainActivity;
import com.inkubator.adryan.learnarabic.activity.MateriDetailActivity;
import com.inkubator.adryan.learnarabic.adapter.ExpandableListAdapter;
import com.inkubator.adryan.learnarabic.adapter.MateriAdapter;
import com.inkubator.adryan.learnarabic.database.DbHelper;
import com.inkubator.adryan.learnarabic.model.Kategori;
import com.inkubator.adryan.learnarabic.model.Materi;
import com.inkubator.adryan.learnarabic.model.MateriDetail;
import com.inkubator.adryan.learnarabic.response.ResponseMateri;
import com.inkubator.adryan.learnarabic.rest.ApiClient;
import com.inkubator.adryan.learnarabic.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adryanev on 05/10/17.
 */

public class MateriFragment extends Fragment{

    private  static final String TAG = MainActivity.class.getSimpleName();
    DbHelper db;

    com.inkubator.adryan.learnarabic.adapter.ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        db = new DbHelper(getContext());
        View view = inflater.inflate(R.layout.fragment_materi,container,false);

        prepareMateri();
        expListView = (ExpandableListView) view.findViewById(R.id.lvexp);
        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {


                String idMateri = listDataHeader.get(groupPosition).substring(0,1);
                String idKategori = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).substring(0,1);

                Integer idSubMateri = db.getSubMateriByMateriKategori(Integer.parseInt(idMateri),Integer.parseInt(idKategori));
                Toast.makeText(getContext(),"idSubMateri = "+idSubMateri.toString(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), MateriDetailActivity.class);
                i.putExtra("idSubMateri",idSubMateri);
                startActivity(i);
                return false;
            }
        });
        //getMateri(view);

        return view;
    }

    private void prepareMateri() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        List<String> namaKate  = new ArrayList<>();

        List<Materi> materis = db.getSemuaMateri();
        List<Kategori>kategoris = db.kategoriList();
        for (Materi m : materis){
            listDataHeader.add(m.getIdMateri()+". "+m.getNamaMateri());
        }
        for(Kategori k : kategoris){
            namaKate.add(k.getIdKategori()+". "+k.getNamaKategori());
        }

        for(int i = 0; i< materis.size(); i++){
            listDataChild.put(listDataHeader.get(i),namaKate);
        }

    }


    /*
    private void getMateri(View view) {
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
    }
    */
}
