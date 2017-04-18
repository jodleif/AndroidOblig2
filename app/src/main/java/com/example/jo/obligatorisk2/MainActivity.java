package com.example.jo.obligatorisk2;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Map;

/**
 * Hovedaktivitet
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateFromSettings();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateFromSettings();
    }

    private void updateFromSettings()
    {
        Map<String, ?> settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getAll();
        String username = (String)settings.get("pref_username");
        Boolean show_u = (Boolean)settings.get("show_username");
        // Sjekke mot null før true
        if(username != null && show_u != null && show_u == true) {
            this.setTitle(String.format("Oblig 2 - %s", username));
        } else {
            this.setTitle("Oblig 2");
        }
    }
    public void showVareListe(View v)
    {
        Intent intent = new Intent(this, VareListe.class);
        startActivity(intent);
    }

    public void showSettings(View v)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
