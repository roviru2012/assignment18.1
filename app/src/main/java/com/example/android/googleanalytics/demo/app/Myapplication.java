package com.example.android.googleanalytics.demo.app;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;


public class Myapplication extends Application {
    public static final String TAG = Myapplication.class
            .getSimpleName();

    private static Myapplication mInstance;

    public static synchronized Myapplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        AnalyticsTrackersClass.initialize(this);
        AnalyticsTrackersClass.getInstance().get(AnalyticsTrackersClass.Target.APP);
    }

    public synchronized Tracker getGoogleAnalyticsTracker() {
        AnalyticsTrackersClass analyticsTrackersClass = AnalyticsTrackersClass.getInstance();
        return analyticsTrackersClass.get(AnalyticsTrackersClass.Target.APP);
    }


    public void trackScreenView(String screenName) {
        Tracker t = getGoogleAnalyticsTracker();

        // Set screen name.
        t.setScreenName(screenName);

        // Send a screen view.
        t.send(new HitBuilders.ScreenViewBuilder().build());

        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }


    public void trackException(Exception e) {
        if (e != null) {
            Tracker t = getGoogleAnalyticsTracker();

            t.send(new HitBuilders.ExceptionBuilder()
                            .setDescription(
                                    new StandardExceptionParser(this, null)
                                            .getDescription(Thread.currentThread().getName(), e))
                            .setFatal(false)
                            .build()
            );
        }
    }

    public void trackEvent(String category, String action, String label) {
        Tracker t = getGoogleAnalyticsTracker();

        // Build and send an Event.
        t.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build());
    }

}