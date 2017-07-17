package com.joshua.craftsman.fragment.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.classify.ClassifyActivity;
import com.joshua.craftsman.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeClassifyPager extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.home_page_classify_construction)
    LinearLayout homePageClassifyConstruction;
    @BindView(R.id.home_page_classify_civilization)
    LinearLayout homePageClassifyCivilization;
    @BindView(R.id.home_page_classify_electric)
    LinearLayout homePageClassifyElectric;
    @BindView(R.id.home_page_classify_road)
    LinearLayout homePageClassifyRoad;
    @BindView(R.id.home_page_classify_water_cons)
    LinearLayout homePageClassifyWaterCons;
    @BindView(R.id.home_page_classify_railway)
    LinearLayout homePageClassifyRailway;
    @BindView(R.id.home_page_classify_mining)
    LinearLayout homePageClassifyMining;
    @BindView(R.id.home_page_classify_airport)
    LinearLayout homePageClassifyAirport;
    @BindView(R.id.home_page_classify_communication)
    LinearLayout homePageClassifyCommunication;

    public int item;
    public String id;
    private int idArray[] = new int[]{R.id.home_page_classify_construction,
            R.id.home_page_classify_civilization, R.id.home_page_classify_electric,
            R.id.home_page_classify_road, R.id.home_page_classify_water_cons,
            R.id.home_page_classify_railway, R.id.home_page_classify_mining,
            R.id.home_page_classify_airport, R.id.home_page_classify_communication};


    public HomeClassifyPager( ) {

    }
    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.home_page_classify, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        homePageClassifyConstruction.setOnClickListener(this);
        homePageClassifyCivilization.setOnClickListener(this);
        homePageClassifyElectric.setOnClickListener(this);
        homePageClassifyRoad.setOnClickListener(this);
        homePageClassifyWaterCons.setOnClickListener(this);
        homePageClassifyRailway.setOnClickListener(this);
        homePageClassifyMining.setOnClickListener(this);
        homePageClassifyAirport.setOnClickListener(this);
        homePageClassifyCommunication.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        for(item=0; item<idArray.length; item++)
            startActivity(new Intent(getActivity(), ClassifyActivity.class));
    }
}
