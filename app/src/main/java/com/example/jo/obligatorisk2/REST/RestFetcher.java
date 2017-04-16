package com.example.jo.obligatorisk2.REST;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jo.obligatorisk2.AConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.jo.obligatorisk2.REST.Type.GET;
import static com.example.jo.obligatorisk2.REST.Type.s;

/**
 * Created by Jo on 16.04.2017.
 */

public class RestFetcher {
    private Fetcher asyncFetcher = new Fetcher();
    static final String URI = String.format("%s/Vare?order=Betegnelse,asc&transform=1", AConfig.APP_URI);
    Callback cb;

    public RestFetcher(Callback cb) {
        this.cb = cb;
    }

    public void getVareListe()
    {
        asyncFetcher.execute();
    }

    private class Fetcher extends AsyncTask<String, String, Long> {
        private String resultat;
        @Override
        protected Long doInBackground(String... params) {
            String jsonResultat = fetchVarer();
            if(jsonResultat == null) {
                resultat = null; // if previous result stored / clear
                return 1l;
            }
            resultat = jsonResultat;
            return 0l;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            if(aLong == 0l) {
                cb.HandleResult(resultat);
            } else {
                Log.d("[RestFetcher]", "Failed fetching results");
                cb.HandleError();
            }
        }

        private String fetchVarer() {
            String varerJSON = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(URI);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod(s(GET));
                connection.connect();

                int status = connection.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String t;
                    StringBuilder sb = new StringBuilder();
                    while ((t = br.readLine()) != null) {
                        sb.append(t);
                    }
                    varerJSON = sb.toString();
                }
            } catch (Exception e) {
                Log.d("[RestFetcher]",
                        String.format("Exception when trying to fetch elements\n%s", e.getMessage()));
            }
            return varerJSON;
        }
    }
}
