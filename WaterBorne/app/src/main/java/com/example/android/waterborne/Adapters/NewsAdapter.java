package com.example.android.waterborne.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.waterborne.MainActivity;
import com.example.android.waterborne.Models.NewsBrief;
import com.example.android.waterborne.NewsActivity;
import com.example.android.waterborne.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Recycler> {
    private static final String TAG = "Bind";
    private ArrayList<NewsBrief> news;
    Context context;

    public NewsAdapter(ArrayList<NewsBrief> news) {
        this.news = news;
    }

    public Recycler onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView;
        itemView = layoutInflater.inflate(R.layout.activity_recycle, parent, false);

        return new Recycler(itemView);

    }

    @Override
    public void onBindViewHolder(Recycler holder, int position) {

        final NewsBrief newsBrief = news.get(position);
        holder.newsTitle.setText(newsBrief.getNewsTitle());
        holder.description.setText(newsBrief.getDescription());
        holder.publishedAt.setText(newsBrief.getPublishedAt());
//        NewsActivity.size = 0;
        holder.expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent =
                        new Intent(Intent.ACTION_VIEW,
                                Uri.parse(newsBrief.getNewsURL().toString()));
                context.startActivity(viewIntent);
            }
        });
        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"News Saved!",Toast.LENGTH_SHORT).show();
            }
        });
        Picasso.get().load(newsBrief.getNewsImageURL()).placeholder(R.mipmap.ic_launcher).error(R.drawable.notfound).into(holder.newsImg);
//        holder.container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        Log.e(TAG, "Position:" + position);

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class Recycler extends RecyclerView.ViewHolder {

        TextView newsTitle;
        TextView description;
        TextView publishedAt;
        ImageView newsImg;
        Button expand;
        Button save;
        LinearLayout container;

        public Recycler(View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.TitleNews);
            description = itemView.findViewById(R.id.Description);
            publishedAt = itemView.findViewById(R.id.PublishedAt);
            newsImg = itemView.findViewById(R.id.ImgNews);
            expand = itemView.findViewById(R.id.expand);
            save = itemView.findViewById(R.id.save);
            //            container=itemView.findViewById(R.id.container);
        }
    }
}

