package com.example.android.googleanalytics.demo.app;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.example.android.googleanalytics.demo.R;

import java.util.HashMap;
import java.util.Map;


public final class AnalyticsTrackersClass {

    private static AnalyticsTrackersClass sInstance;
    private final Map<Target, Tracker> mTrackers = new HashMap<Target, Tracker>();
    private final Context mContext;

    private AnalyticsTrackersClass(Context context) {
        mContext = context.getApplicationContext();
    }

    public static synchronized void initialize(Context context) {
        if (sInstance != null) {
            throw new IllegalStateException("Extra call to initialize analytics trackers");
        }

        sInstance = new AnalyticsTrackersClass(context);
    }

    public static synchronized AnalyticsTrackersClass getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("Call initialize() before getInstance()");
        }

        return sInstance;
    }

    public synchronized Tracker get(Target target) {
        if (!mTrackers.containsKey(target)) {
            Tracker tracker;
            switch (target) {
                case APP:
                    tracker = GoogleAnalytics.getInstance(mContext).newTracker(R.xml.global_tracker);
                    break;
                default:
                    throw new IllegalArgumentException("Unhandled analytics target " + target);
            }
            mTrackers.put(target, tracker);
        }

        return mTrackers.get(target);
    }

    public enum Target {
        APP,
        // Add more trackers here if you need, and update the code in #get(Target) below
    }
}
