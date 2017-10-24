package com.example.courtney.gauss;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_VIDEO_CAPTURE = 1;

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();
            mVideoView.setVideoURI(videoUri);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toast.makeText(getBaseContext(), "WORKING! You are not connected to the VTM's wireless network." +
                " \nConnect or view saved content.", Toast.LENGTH_LONG).show();


        Button record = (Button)findViewById(R.id.record_video_btn);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispatchTakeVideoIntent();

            }
        });
        Button wifi = (Button)findViewById(R.id.find_network_btn);
        wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

            }
        });

        Button viewRecordings = (Button)findViewById(R.id.view_recordings_btn);
        viewRecordings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        Button connectedView = (Button)findViewById(R.id.connected_view_btn);
        connectedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(MainActivity.this, VTMConnectedActivity.class);
                startActivity(startIntent);

            }
        });


    }
}
