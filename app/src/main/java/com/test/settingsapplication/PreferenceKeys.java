package com.test.settingsapplication;

import android.content.SharedPreferences;

/**
 * Created by gabriel on 27/09/2017.
 */

public class PreferenceKeys {

    public static final String RECORD_LOCALLY = "prefs-record-locally";
    public static final String PREFERRED_SERVER = "prefs-preferred-server";
    public static final String STREAM_QUALITY = "prefs-preferred-quality";

    public static final boolean DEFAULT_RECORD_LOCALLY = false;


    private PreferenceKeys() {
    }

    public static String getContinent(SharedPreferences prefs, String defaultCode){
        String prefererredServer = prefs.getString(PreferenceKeys.PREFERRED_SERVER, defaultCode);
        return prefererredServer;
    }

    public static Boolean getSaveLocally(SharedPreferences sharedPreferences) {
        boolean recordLocally = sharedPreferences.getBoolean(PreferenceKeys.RECORD_LOCALLY, DEFAULT_RECORD_LOCALLY);
        return recordLocally;
    }

}
