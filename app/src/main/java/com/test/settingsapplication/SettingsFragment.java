package com.test.settingsapplication;

/**
 * Created by gabriel on 27/09/2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabriel on 26/05/2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat {


    private ListPreference mContinentPreference;
    private ListPreference mQualityPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Server s1 = new Server();
        s1.setName("Server1");
        s1.setContinentCode("EU");
        Server s2 = new Server();
        s2.setName("Server2");
        s2.setContinentCode("NA");

        List<Server> servers = new ArrayList<>();
        servers.add(s1);
        servers.add(s2);
        setServerList(servers, "EU");



        setQualityList();
    }

    private void setQualityList() {
        List<String> entries = new ArrayList<>();
        List<String> values = new ArrayList<>();

        entries.add("Bad (500)");
        entries.add("Medium (1000)");
        entries.add("High (2000)");

        values.add("500");
        values.add("1000");
        values.add("1500");

        mContinentPreference.setEntries((entries.toArray(new String[entries.size()])));
        mContinentPreference.setEntryValues((values.toArray(new String[values.size()])));
    }


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.stream_preferences);
        mContinentPreference = (ListPreference) findPreference(PreferenceKeys.PREFERRED_SERVER);
        mQualityPreference = (ListPreference) findPreference(PreferenceKeys.STREAM_QUALITY);

        setHasOptionsMenu(true);
    }

    public void setServerList(List<Server> servers, String preferredServer) {

        List<String> entries = new ArrayList<>();
        List<String> entryValues = new ArrayList<>();

        for (Server server : servers) {
            entries.add(String.format("%s (%s)", server.getName(), server.getContinentCode()));
            entryValues.add(server.getContinentCode());
        }
        mQualityPreference.setEntries((entries.toArray(new String[entries.size()])));
        mQualityPreference.setEntryValues((entryValues.toArray(new String[entryValues.size()])));
    }
}
