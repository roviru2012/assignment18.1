package com.example.android.googleanalytics.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.googleanalytics.demo.app.Myapplication;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView btnActivityTracking, btnFragmentTracking, btnEventTracking, btnExceptionTracking, btnCrashTracking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();
        initView();

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Google Analytics Example");
            setSupportActionBar(toolbar);
        }
    }

    private void initView() {
        btnActivityTracking = (TextView) findViewById(R.id.btnActivityTracking);
        btnFragmentTracking = (TextView) findViewById(R.id.btnFragmentTracking);
        btnEventTracking = (TextView) findViewById(R.id.btnEventTracking);
        btnExceptionTracking = (TextView) findViewById(R.id.btnExceptionTracking);
        btnCrashTracking = (TextView) findViewById(R.id.btnCrashTracking);

        btnActivityTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SampleActivity.class);
                startActivity(intent);
            }
        });

        btnFragmentTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SampleFragment sampleFragment = SampleFragment.newInstance();
                fragmentTransaction.replace(R.id.main_container, sampleFragment);
                fragmentTransaction.commit();
            }
        });

        btnEventTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // category, action, label
                Myapplication.getInstance().trackEvent("Event Tracking", "Event Fire", "Sample Event");

                Toast.makeText(getApplicationContext(), "Event 'Book' 'Download' 'Event example' is sent.Check it on Google Analytics Dashboard!", Toast.LENGTH_LONG).show();
            }
        });

        btnExceptionTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = null;
                    if (name.equals("Android Google Analytics Demo")) {
                        //throw null pointer exception
                    }
                } catch (Exception e) {
                    // Tracking exception
                    Myapplication.getInstance().trackException(e);

                    Toast.makeText(getApplicationContext(), "Exception Fire", Toast.LENGTH_LONG).show();


                }
            }
        });

        btnCrashTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "App Crash Fire", Toast.LENGTH_LONG).show();
                throw new RuntimeException("This is a crash");
            }
        });
    }


}
