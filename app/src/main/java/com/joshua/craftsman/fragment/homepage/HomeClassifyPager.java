package com.joshua.craftsman.fragment.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.classify.ClassifyAirportActivity;
import com.joshua.craftsman.activity.classify.ClassifyCommunicationsActivity;
import com.joshua.craftsman.activity.classify.ClassifyHighWaysActivity;
import com.joshua.craftsman.activity.classify.ClassifyHouseActivity;
import com.joshua.craftsman.activity.classify.ClassifyMechanicalActivity;
import com.joshua.craftsman.activity.classify.ClassifyMiningActivity;
import com.joshua.craftsman.activity.classify.ClassifyMunicipalActivity;
import com.joshua.craftsman.activity.classify.ClassifyRailWayActivity;
import com.joshua.craftsman.activity.classify.ClassifyWaterConservancyActivity;
import com.joshua.craftsman.activity.find.FindActivityActivity;
import com.joshua.craftsman.activity.find.FriendCircleActivity;
import com.joshua.craftsman.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeClassifyPager extends BaseFragment implements View.OnClickListener{

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

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.home_page_classify, null);
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
        switch (v.getId()) {
            case R.id.home_page_classify_construction:
                startActivity(new Intent(getActivity(),ClassifyHouseActivity.class));
                break;
            case R.id.home_page_classify_civilization:
                startActivity(new Intent(getActivity(),ClassifyMunicipalActivity.class));
                break;
            case R.id.home_page_classify_electric:
                startActivity(new Intent(getActivity(),ClassifyMechanicalActivity.class));
                break;
            case R.id.home_page_classify_road:
                startActivity(new Intent(getActivity(),ClassifyHighWaysActivity.class));
                break;
            case R.id.home_page_classify_water_cons:
                startActivity(new Intent(getActivity(),ClassifyWaterConservancyActivity.class));
                break;
            case R.id.home_page_classify_railway:
                startActivity(new Intent(getActivity(),ClassifyRailWayActivity.class));
                break;
            case R.id.home_page_classify_mining:
                startActivity(new Intent(getActivity(),ClassifyMiningActivity.class));
                break;
            case R.id.home_page_classify_airport:
                startActivity(new Intent(getActivity(),ClassifyAirportActivity.class));
                break;
            case R.id.home_page_classify_communication:
                startActivity(new Intent(getActivity(),ClassifyCommunicationsActivity.class));
                break;
        }
    }

}
