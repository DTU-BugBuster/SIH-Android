package com.example.android.waterborne.DiseasesHeatMapRelated;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.waterborne.R;

import java.util.ArrayList;


public class DiseaseCountSheet extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    private ArrayList<ReportedCases> rc_mod;
    TextView d_count ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.disease_bottom_sheet, container, false);
        d_count = v.findViewById(R.id.disease_count);
        rc_mod = HeatmapsDemoActivity.rc_mod;
        d_count.setText(String.valueOf(rc_mod.size()));
        return v;
    }

    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
}