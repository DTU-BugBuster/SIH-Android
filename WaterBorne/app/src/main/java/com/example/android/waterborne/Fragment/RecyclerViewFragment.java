package com.example.android.waterborne.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.waterborne.Adapters.TestRecyclerViewAdapter;
import com.example.android.waterborne.HomeRemedies.HomeRemedy;
import com.example.android.waterborne.Models.HomeRemedyModel;
import com.example.android.waterborne.R;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewFragment extends Fragment {

    private static final int ITEM_COUNT = 1;
    public static ArrayList<String> headings;
    public static ArrayList<String> descriptions;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static RecyclerViewFragment newInstance() {

        headings = new ArrayList<String>();
        descriptions = new ArrayList<>();

        for (int i=0; i  < 4; i++){
            headings.add("Heading " + i + 1);
            descriptions.add("Description " + i + 1);
        }

        return new RecyclerViewFragment();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        final ArrayList<HomeRemedyModel> items = new ArrayList<HomeRemedyModel>();

        for (int i = 0; i < ITEM_COUNT; ++i) {
            items.add(new HomeRemedyModel(descriptions.get(HomeRemedy.currentPosition),headings.get(HomeRemedy.currentPosition)));
        }


            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new TestRecyclerViewAdapter(items));
    }
}
