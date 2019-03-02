package com.example.android.waterborne;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.waterborne.Adapters.NewsAdapter;
import com.example.android.waterborne.Models.NewsBrief;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsActivity extends AppCompatActivity //implements NavigationView.OnNavigationItemSelectedListener
{
    private static final String TAG = "OKHTTPCallBack";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.RecycleNewsHealth);
        String url = "https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=704e22f755d64beea5360a1d8dd6509e";
        makeConnection(url);
    }

    private void makeConnection(String urlS) {

        try {
            URL url = new URL(urlS);
            OkHttpClient okHttpClient = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "Error");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String ans = response.body().string();
                    ArrayList<NewsBrief> newsBriefs = null;
                    try {
                        newsBriefs = parseJson(ans);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    final ArrayList<NewsBrief> finalNewsBriefs = newsBriefs;
                    Log.e(TAG, "Size = " + newsBriefs.size());
                    NewsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            NewsAdapter recycleViewAdapter = new NewsAdapter(finalNewsBriefs);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                            recyclerView.setAdapter(recycleViewAdapter);
//                            recycleViewAdapter.notifyDataSetChanged();
                        }
                    });


                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    private ArrayList<NewsBrief> parseJson(String ans) throws JSONException {
        ArrayList<NewsBrief> newsBriefs = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(ans);
        JSONArray jsonArray = jsonObject.getJSONArray("articles");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String title = object.getString("title");
            String imgURL = object.getString("urlToImage");
            String description = object.getString("description");
            String newsURL = object.getString("url");
            String publishedAt = object.getString("publishedAt");
            NewsBrief component = new NewsBrief(title, imgURL, description, newsURL, publishedAt);
            newsBriefs.add(component);
        }

        return newsBriefs;
    }

}

