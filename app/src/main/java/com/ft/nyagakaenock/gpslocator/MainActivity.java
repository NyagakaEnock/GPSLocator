package com.ft.nyagakaenock.gpslocator;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;


public class MainActivity extends AppCompatActivity {
private Button btnstart,btnEnd;
    private Toolbar toolbar;
    private TextView info;
    GPSTracker gpsTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" GPS Locator");
        getSupportActionBar().setIcon(R.drawable.iconx);
        btnstart = (Button)findViewById(R.id.button);
        btnEnd = (Button)findViewById(R.id.button2);
        info = (TextView)findViewById(R.id.textView2);
        final SharedPreferences prefs = this.getSharedPreferences( "MySess", getApplicationContext().MODE_PRIVATE);


        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsTracker = new GPSTracker(MainActivity.this);

                // check if GPS enabled
                if(gpsTracker.canGetLocation()){

                    double latitude = gpsTracker.getLatitude();
                    double longitude = gpsTracker.getLongitude();
                 Toast.makeText(getApplicationContext(),"App is Running: Latitude "+latitude+" Longitude "+longitude,Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("OnJourney","Yes");
                    editor.apply();
                    info.setText("App is Running...");
                    info.setTextColor(getResources().getColor(R.color.colorText));
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gpsTracker.showSettingsAlert();
                }
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               gpsTracker = new GPSTracker(MainActivity.this);
                String name = prefs.getString("OnJourney", "");
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                   info.setText("App Stopped ");
                info.setTextColor(getResources().getColor(R.color.colorAccent));
                    gpsTracker.stopUsingGPS();



            }
        });
    }





}
