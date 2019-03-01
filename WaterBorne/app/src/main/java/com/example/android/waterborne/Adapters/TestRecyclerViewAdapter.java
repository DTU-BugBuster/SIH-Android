package com.example.android.waterborne.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.waterborne.Models.HomeRemedyModel;
import com.example.android.waterborne.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.waterborne.Adapters.TestListViewAdapter.TYPE_HEADER;

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder> {

    ArrayList<HomeRemedyModel> contents;


    public TestRecyclerViewAdapter(ArrayList<HomeRemedyModel> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView;

        itemView = li.inflate(R.layout.list_item_card_big,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
      HomeRemedyModel  homeRemedyModel = contents.get(i);
      viewHolder.tvHeading.setText(homeRemedyModel.getHeading());
      viewHolder.tvDescription.setText(homeRemedyModel.getDescription());
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvHeading, tvDescription;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             tvHeading = itemView.findViewById(R.id.tvHeading);
             tvDescription = itemView.findViewById(R.id.tvDescription);
         }
     }
}