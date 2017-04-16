package com.example.jo.obligatorisk2.REST;

import android.os.AsyncTask;

import com.example.jo.obligatorisk2.DataModell.Vare;

/**
 * Created by Jo on 16.04.2017.
 */

public class RestAdapter {

    private VareAdapter asyncAdapter = new VareAdapter();
    public void insertVare(Vare vare)
    {

    }
    private class VareAdapter extends AsyncTask<String, String, Long> {

        @Override
        protected Long doInBackground(String... params) {
            return null;
        }
    }
}
