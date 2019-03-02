package com.example.android.waterborne;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.waterborne.Adapters.MenuAdapter;
import com.example.android.waterborne.Auth.LoginActivity;
import com.example.android.waterborne.Auth.SignupActivity;
import com.example.android.waterborne.ChatApp.AnonymousChat;
import com.example.android.waterborne.ChatBotRelated.ChatbotActivity;
import com.example.android.waterborne.Chatbot.ChatActivity;
import com.example.android.waterborne.DiseasesHeatMapRelated.HeatmapsDemoActivity;
import com.example.android.waterborne.HomeRemedies.HomeRemedy;
import com.example.android.waterborne.IsPlaceSafe.IsPlaceSafeActivity;
import com.example.android.waterborne.Models.Item;
import com.example.android.waterborne.NearbyHospitalsRelated.NearbyHospitalsActivity;
import com.github.tbouron.shakedetector.library.ShakeDetector;
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

public class MenuScreen extends AppCompatActivity implements View.OnClickListener, MenuAdapter.ItemListener, ShakeDetector.OnShakeListener {

    private static final long RIPPLE_DURATION = 250;
    Button tvSwitch;
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
    static boolean shake = true;
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

        if (SignupActivity.switchNumber == 0){

            arrayList.add(new Item(getString(R.string.chat_doctor), R.drawable.ic_doctor, "#ffffff"));
            arrayList.add(new Item(getString(R.string.test_ai), R.drawable.ic_ai_testing, "#ffffff"));
            arrayList.add(new Item(getString(R.string.chatbot), R.drawable.ic_chat_bot , "#ffffff"));
            arrayList.add(new Item(getString(R.string.news_water), R.drawable.ic_newspaper, "#ffffff"));
            arrayList.add(new Item(getString(R.string.is_safe), R.drawable.tsunami_colorless, "#ffffff"));
            arrayList.add(new Item(getString(R.string.input),R.drawable.form,"#ffffff"));
            arrayList.add(new Item(getString(R.string.home_remedies), R.drawable.medical, "#ffffff"));
            arrayList.add(new Item(getString(R.string.hospital), R.drawable.ic_nearest_hosp, "#ffffff"));
            arrayList.add(new Item(getString(R.string.heatmap), R.drawable.ic_heatmap, "#ffffff"));
        }
        else{

            arrayList.add(new Item(getString(R.string.chat_doctor_h), R.drawable.ic_doctor, "#ffffff"));
            arrayList.add(new Item(getString(R.string.test_ai_h), R.drawable.ic_ai_testing, "#ffffff"));
            arrayList.add(new Item(getString(R.string.chatbot_h), R.drawable.ic_chat_bot , "#ffffff"));
            arrayList.add(new Item(getString(R.string.news_water_h), R.drawable.ic_newspaper, "#ffffff"));
            arrayList.add(new Item(getString(R.string.is_safe_h), R.drawable.tsunami_colorless, "#ffffff"));
            arrayList.add(new Item(getString(R.string.input_h),R.drawable.form,"#ffffff"));
            arrayList.add(new Item(getString(R.string.home_remedies_h), R.drawable.medical, "#ffffff"));
            arrayList.add(new Item(getString(R.string.hospital_h), R.drawable.ic_nearest_hosp, "#ffffff"));
            arrayList.add(new Item(getString(R.string.heatmap_h), R.drawable.ic_heatmap, "#ffffff"));
        }

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

        tvSwitch = v.findViewById(R.id.tvSwitch);
        tvpaytm = v.findViewById(R.id.tvpaytm);
        tvhosp = v.findViewById(R.id.tvhosp);
        tvsignout = v.findViewById(R.id.tvsignout);


        tvSwitch.setOnClickListener(this);
        tvpaytm.setOnClickListener(this);
        tvhosp.setOnClickListener(this);
        tvsignout.setOnClickListener(this);

        if (ShakeDetector.create(this, this)) {
//            final float sensibility = (float) (mSensibility.getProgress() + 10) / 10;
//            ShakeDetector.updateConfiguration(sensibility, mShakeNumber.getProgress());
            ;

            Toast.makeText(this, "Shake to call activated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Shake to call disabled", Toast.LENGTH_SHORT).show();
        }

        convertToHindi(SignupActivity.switchNumber);

    }

    private void convertToHindi(int switchNumber) {
       if (switchNumber == 0)
           return;
        TextView tvMenuscreenHeading = findViewById(R.id.tvMenuscreenHeading);
        tvMenuscreenHeading.setText("जल जनित");
        Button tvHosp = findViewById(R.id.tvhosp);
        tvHosp.setText(getString(R.string.shake_to_call_h));

        Button tvpaytm = findViewById(R.id.tvpaytm);
        tvpaytm.setText(getText(R.string.paytm_karo_h));

        Button signOut = findViewById(R.id.tvsignout);
        signOut.setText(getString(R.string.sign_out_h));
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
            case R.id.tvSwitch:
            {
                SignupActivity.switchNumber = 1 - SignupActivity.switchNumber;
            }
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
//        startActivity(new Intent(this,NearbyHospitalsActivity.class));

        if (shake){
            shake = false;
            if(SignupActivity.switchNumber == 0)
               Toast.makeText(this, "Shake to call disabled", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "अक्षम कॉल करने के लिए हिलाएँ", Toast.LENGTH_SHORT).show();
            ShakeDetector.destroy();
            v.setBackgroundColor(Color.rgb(255,0,0));

        }
        else{
            shake = true;
            if (SignupActivity.switchNumber == 0)
                Toast.makeText(this, "Shake to call enabled", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "सक्षम कॉल करने के लिए हिला", Toast.LENGTH_SHORT).show();
            ShakeDetector.create(this, this);
            v.setBackgroundColor(Color.parseColor("#19783b"));

        }

    }
    public void paytm(View v) {
        if (SignupActivity.switchNumber == 0)
            Toast.makeText(this,"Paytm ",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Paytm ",Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this,PaytmActivity.class));
    }

    public void anonymousChat(View v) {
        startActivity(new Intent(this,AnonymousChat.class));
    }

    @Override
    public void onItemClick(Item item) {
        if (SignupActivity.switchNumber == 0)
           Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), item.text + " क्लिक किया है", Toast.LENGTH_SHORT).show();

        if (item.getText().equals("Chat with a doctor") || item.getText().equals("डॉक्टर से चैट करें")) {
            startActivity(new Intent(getBaseContext(), HeatmapsDemoActivity.class));
        } else if (item.getText().equals("Test with AI") || item.getText().equals("एआई के साथ परीक्षण")) {
//            startActivity(new Intent(getBaseContext(),TestWithAI.class));
            ;
//            Toast.makeText(this, "Don't blame me ask pranav xD", Toast.LENGTH_SHORT).show();
        } else if (item.getText().equals("Chatbot") || item.getText().equals("चैट बॉट")) {
            startActivity(new Intent(getBaseContext(), ChatActivity.class));
        } else if (item.getText().equals("News") || item.getText().equals("समाचार")) {
            startActivity(new Intent(getBaseContext(),NewsActivity.class));
            Toast.makeText(this, "Will add in next version", Toast.LENGTH_SHORT).show();
        } else if (item.getText().equals("Is Place Safe") || item.getText().equals("जगह सुरक्षित है")) {
            startActivity(new Intent(getBaseContext(),IsPlaceSafeActivity.class));
//            Toast.makeText(this, "Don't blame me ask pranav xD", Toast.LENGTH_SHORT).show();
           ;
        } else if (item.getText().equals("Home Remedies") || item.getText().equals("घरेलू उपचार")) {
            startActivity(new Intent(getBaseContext(),HomeRemedy.class));
            Toast.makeText(this, "Will add in next version", Toast.LENGTH_SHORT).show();
        } else if (item.getText().equals("Nearest Hospital") || item.getText().equals("नजदीकी अस्पताल")) {
            startActivity(new Intent(getBaseContext(), NearbyHospitalsActivity.class));
        } else if (item.getText().equals("Prediction of loss") || item.getText().equals("नुकसान की भविष्यवाणी")) {
//            startActivity(new Intent(getBaseContext(),PredictLoss.class));
//            Toast.makeText(this, "Don't blame me ask pranav xD", Toast.LENGTH_SHORT).show();

            ;
        } else if(item.getText().equals("Provide some input") || item.getText().equals("कुछ इनपुट दें")){
            startActivity(new Intent(getBaseContext(),QuestionnaireActivity.class));
        }
        else {
            if (SignupActivity.switchNumber == 0)
               Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "त्रुटि! नुकसान की", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnShake() {
        if(checkPhonePermission()){
            if(shake) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8851506992"));
                startActivity(intent);
            }

        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, 123);

        }

    }


    public boolean checkPhonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
            }
            return false;

        } else
            return true;
    }
}