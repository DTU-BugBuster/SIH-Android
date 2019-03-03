package com.example.android.waterborne.ReportIssuesRelated;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.waterborne.MenuScreen;
import com.example.android.waterborne.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ReportProblemActivity extends AppCompatActivity implements LocationListener {
    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText name, age, gender, disease, pincode, h_name, state;
    private ProgressDialog pd;
    Button report;
    private String lat = "25.2623", lng = "82.9894";

    private DatabaseReference mDatabaseRef;
    FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    private FirebaseAuth mauth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_problem);

        report = findViewById(R.id.bt_submit);
        name = findViewById(R.id.et_Name);
        age = findViewById(R.id.et_age);
        gender = findViewById(R.id.etGender);
        disease = findViewById(R.id.et_disease);
        h_name = findViewById(R.id.et_h_name);
        pincode = findViewById(R.id.et_pincode);
        state = findViewById(R.id.et_state);

        mauth = FirebaseAuth.getInstance();
        mCurrentUser = mauth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("cases");
        firebaseUser = firebaseAuth.getCurrentUser();


        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Report").child(firebaseUser.getUid());
        pd = new ProgressDialog(this);

        report.setOnClickListener(v -> {
            pd.setMessage("Upload in progress");
            pd.show();
            uploadFile();

        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
//                && data != null && data.getData() != null) {
//            mImageUri = data.getData();
//
//            Picasso.get().load(mImageUri).into(image);
//        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        UploadReport upload = new UploadReport(name.getText().toString().trim(), Integer.parseInt(age.getText().toString()), gender.getText().toString(), disease.getText().toString(), pincode.getText().toString().trim(), h_name.getText().toString(), state.getText().toString(), mCurrentUser.getUid(), lat,lng);
        String uploadId = mDatabaseRef.push().getKey();
        mDatabaseRef.child(uploadId).setValue(upload);
        pd.dismiss();
        Toast.makeText(ReportProblemActivity.this, "Upload successful", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(ReportProblemActivity.this, MenuScreen.class));

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
