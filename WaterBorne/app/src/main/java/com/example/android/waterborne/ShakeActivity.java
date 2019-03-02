package com.example.android.waterborne;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.tbouron.shakedetector.library.ShakeDetector;

public class ShakeActivity extends AppCompatActivity implements ShakeDetector.OnShakeListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private static final String STATUS = "status";
    private static final String SENSIBILITY = "sensibility";
    private static final String SHAKE_NUMBER = "shake_number";

    protected TextView mStatus;
    protected DrawerLayout mDrawerLayout;
    protected LinearLayout mRightDrawer;
    protected SeekBar mSensibility;
    protected SeekBar mShakeNumber;
    protected TextView mSensibilityLabel;
    protected TextView mShakeNumberLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        mStatus = findViewById(R.id.status);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mRightDrawer = findViewById(R.id.right_drawer);
        mSensibility = findViewById(R.id.sensibility);
        mShakeNumber = findViewById(R.id.shake_number);
        mShakeNumberLabel = findViewById(R.id.shake_number_label);


        if (savedInstanceState != null && savedInstanceState.containsKey(STATUS)) {
            mStatus.setText(savedInstanceState.getString(STATUS));
            mSensibility.setProgress(savedInstanceState.getInt(SENSIBILITY));
            mShakeNumber.setProgress(savedInstanceState.getInt(SHAKE_NUMBER));
        }

        // We create and start the shake detector here.
        if (ShakeDetector.create(this, this)) {
            final float sensibility = (float) (mSensibility.getProgress() + 10) / 10;
            ShakeDetector.updateConfiguration(sensibility, mShakeNumber.getProgress());
        } else {
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        // We stop shake detector when the activity goes to the background.
        ShakeDetector.stop();

//        addStatusMessage(getString(R.string.shake_detector_stopped));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // We destroy shake detector when the activity finishes to release the resources.
        ShakeDetector.destroy();

//        addStatusMessage(getString(R.string.shake_detector_destroyed));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATUS, mStatus.getText().toString());
        outState.putInt(SENSIBILITY, mSensibility.getProgress());
        outState.putInt(SHAKE_NUMBER, mShakeNumber.getProgress());
    }


    @Override
    public void OnShake() {
        // This callback is triggered by the ShakeDetector. In a real implementation, you should
        // do here a real action.
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8851506992"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            startActivity(intent);
            return;
        } else {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE}, 123
                );

            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 123: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ShakeDetector.create(this, this);

                } else {
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }


}
