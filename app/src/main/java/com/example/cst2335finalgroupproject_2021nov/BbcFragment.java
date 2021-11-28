package com.example.cst2335finalgroupproject_2021nov;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class BbcFragment extends Fragment {
    /**
     * fragment class, similar to labs
     */

    public BbcFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /**
         * fragment inflate
         */

        return inflater.inflate(R.layout.fragment_bbc, container, false);
    }
}
