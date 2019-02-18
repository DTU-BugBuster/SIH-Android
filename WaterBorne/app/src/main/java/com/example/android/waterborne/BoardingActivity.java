package com.example.android.waterborne;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class BoardingActivity extends AhoyOnboarderActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("24x7 assistance", "Stay connected with doctors and medical experts and leverage instant support in case of emergency", R.drawable.patient);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("AI based chatbot", "IBM based doctor chatbot for guidance in case of any disorder.", R.drawable.robot);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Timed Alerts and Notifications", "Stay notified with all government announcements and prevention techniques with our notification services", R.drawable.announcement);
        AhoyOnboarderCard ahoyOnboarderCard4 = new AhoyOnboarderCard("Is Place Safe ?", "Visiting a new place ? Check if it safe or not. Rain and terrain play a vital role!", R.drawable.tsunami);
        AhoyOnboarderCard ahoyOnboarderCard5 = new AhoyOnboarderCard("Nearby Hospitals", "Emergency ? We'll connect you with all nearby hospitals in no time with google maps support.", R.drawable.map);
        AhoyOnboarderCard ahoyOnboarderCard6 = new AhoyOnboarderCard("Paytm Payment Support", "Pay doctors and book appointments comfortably. Paytm Karo!", R.drawable.paytm);


        ahoyOnboarderCard1.setTitleColor(android.R.color.black);
        ahoyOnboarderCard1.setDescriptionColor(android.R.color.black);
        ahoyOnboarderCard2.setTitleColor(android.R.color.black);
        ahoyOnboarderCard2.setDescriptionColor(android.R.color.black);
        ahoyOnboarderCard3.setTitleColor(android.R.color.black);
        ahoyOnboarderCard3.setDescriptionColor(android.R.color.black);
        ahoyOnboarderCard4.setTitleColor(android.R.color.black);
        ahoyOnboarderCard4.setDescriptionColor(android.R.color.black);
        ahoyOnboarderCard5.setTitleColor(android.R.color.black);
        ahoyOnboarderCard5.setDescriptionColor(android.R.color.black);
        ahoyOnboarderCard6.setTitleColor(android.R.color.black);
        ahoyOnboarderCard6.setDescriptionColor(android.R.color.black);


        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard4.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard5.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard6.setBackgroundColor(R.color.black_transparent);





        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        pages.add(ahoyOnboarderCard4);
        pages.add(ahoyOnboarderCard5);
        pages.add(ahoyOnboarderCard6);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
        }

        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        //setGradientBackground();
        setImageBackground(R.drawable.boarding);

//        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        //setFont(face);

        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.white);

        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {
//        Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getBaseContext(),SplashActivity.class));
    }
}
