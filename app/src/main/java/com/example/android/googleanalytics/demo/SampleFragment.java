package com.example.android.googleanalytics.demo;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.googleanalytics.demo.app.Myapplication;


public class SampleFragment extends Fragment {


    public static SampleFragment newInstance() {
        SampleFragment fragment = new SampleFragment();

        return fragment;
    }

    public SampleFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView= inflater.inflate(R.layout.fragment_sample, container, false);
        return convertView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // for tracking manual fragment
        Myapplication.getInstance().trackScreenView("Sample Fragment");

    }
}
