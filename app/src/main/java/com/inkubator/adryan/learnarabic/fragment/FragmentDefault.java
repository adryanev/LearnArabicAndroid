package com.inkubator.adryan.learnarabic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inkubator.adryan.learnarabic.R;

/**
 * Created by adryanev on 05/10/17.
 */

public class FragmentDefault extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout,container,false);
        return view;
    }
}
