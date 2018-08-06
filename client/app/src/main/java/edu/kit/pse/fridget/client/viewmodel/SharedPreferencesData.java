package edu.kit.pse.fridget.client.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;


public class SharedPreferencesData {

    public String DEFAULT ="N/A";

    //Diese Methode dient zum abrufen der Daten aus dem Default shared Preferences file : edu.kit.pse.fridget.client_preferences.xml

    public String getSharedPreferencesData (String key, View v)
    { Context context = v.getContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String data =sharedPreferences.getString(key,DEFAULT);

        return  data;

    }
}
