package com.example.android.waterborne;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.android.waterborne.Adapters.MenuAdapter;
import com.example.android.waterborne.Auth.LoginActivity;
import com.example.android.waterborne.ChatApp.AnonymousChat;
import com.example.android.waterborne.Models.Item;
import com.example.android.waterborne.NearbyHospitalsRelated.NearbyHospitalsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.yalantis.guillotine.animation.GuillotineAnimation;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MenuScreen extends AppCompatActivity implements View.OnClickListener, MenuAdapter.ItemListener{

    private static final long RIPPLE_DURATION = 250;
    Button tvchat;
    Button tvpaytm;
    Button tvhosp;
    Button tvsignout;
    private WebView chatWindow;
    private RecyclerView recyclerView;
    private ArrayList<Item> arrayList;
    private FirebaseAuth mAuth;


    static int[] imageResources = new int[]{
            R.drawable.emotion,
            R.drawable.music_player,
            R.drawable.robot,
            R.drawable.project,
            R.drawable.clown,

    };
    static int[] Strings = new int[]{
            R.string.voice,
            R.string.news,
            R.string.weather,
            R.string.forum,
            R.string.buy,

    };

    Toolbar toolbar;
    FrameLayout root;
    View contentHamburger;

    static int imageResourceIndex = 0;
    static int str = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());


        mAuth = FirebaseAuth.getInstance();
        Toast.makeText(this, "Welcome " + mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();

        arrayList.add(new Item("Chat with a doctor", R.drawable.patient_colourless, "#0A9B88"));
        arrayList.add(new Item("Test with AI", R.drawable.chemistry_colourless, "#3E51B1"));
        arrayList.add(new Item("Chatbot", R.drawable.robot_colourless, "#673BB7"));
        arrayList.add(new Item("News", R.drawable.newspaper, "#4BAA50"));
        arrayList.add(new Item("Is Place Safe", R.drawable.tsunami_colorless, "#F94336"));
        arrayList.add(new Item("Home Remedies", R.drawable.medical, "#0A9B88"));


        MenuAdapter menuAdapter = new MenuAdapter(this, arrayList, this);
        recyclerView.setAdapter(menuAdapter);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Water Borne");
        }


        root = findViewById(R.id.root);
        toolbar = findViewById(R.id.toolbar);
        contentHamburger = findViewById(R.id.content_hamburger);

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);


        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
//
//        LayoutInflater li  = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        assert li != null;
        View v = LayoutInflater.from(this).inflate(R.layout.guillotine, null);

        tvchat = v.findViewById(R.id.tvchat);
        tvpaytm = v.findViewById(R.id.tvpaytm);
        tvhosp = v.findViewById(R.id.tvhosp);
        tvsignout = v.findViewById(R.id.tvsignout);


        tvchat.setOnClickListener(this);
        tvpaytm.setOnClickListener(this);
        tvhosp.setOnClickListener(this);
        tvsignout.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public static int getString() {
        if (str >= Strings.length) str = 0;
        return Strings[str++];
    }
    public static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }
    public void start(int pos)
    {
        //Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
        Intent in = new Intent( this, MainActivity.class);
        startActivity(in);
    }
    public void stock(int pos)
    {
        // Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent( this, ChatActivity.class);
//        startActivity(in);
    }
    public void sales(int pos)
    {
        //Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent( this, Spacewar.class);
//        startActivity(in);
    }
    public void buy(int pos)
    {
        // Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent( this, MemeActivity.class);
//        startActivity(in);
    }
    public void anonymous(int pos)
    {
        // Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
        Intent in = new Intent( this, AnonymousChat.class);
        startActivity(in);
    }

    public void ordering(int pos)
    {
        //Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent( this, MusicActivity.class);
//        startActivity(in);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tvpaytm:
            {
                paytm(null);
                break;
            }
            case R.id.tvchat:
            { anonymousChat(null); break;}
            case R.id.tvhosp:
            {hosp(null); break;}
            case R.id.tvsignout:
            {
//                FirebaseAuth.getInstance().signOut();
                login(null);
                break;}


        }
    }

    public void login(View v ) {
        startActivity(new Intent(getBaseContext(),LoginActivity.class));
        finish();
    }

    public void hosp(View v) {
        startActivity(new Intent(this,NearbyHospitalsActivity.class));
    }
    public void paytm(View v) {
        Toast.makeText(this,"Paytm ",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,PaytmActivity.class));
    }

    public void anonymousChat(View v) {
        startActivity(new Intent(this,AnonymousChat.class));
    }

    @Override
    public void onItemClick(Item item) {
        Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
    }

}