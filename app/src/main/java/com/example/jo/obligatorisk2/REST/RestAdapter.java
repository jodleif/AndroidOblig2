package com.example.jo.obligatorisk2.REST;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jo.obligatorisk2.AConfig;
import com.example.jo.obligatorisk2.DataModell.Vare;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.jo.obligatorisk2.REST.Type.*;

/**
 * Created by Jo on 16.04.2017.
 */

public class RestAdapter {

    private VareAdapter asyncAdapter = new VareAdapter();
    private static final String uri = AConfig.APP_URI + "/Vare";
    private static final String[] CONTENT_TYPE = {"content-type", "application/json; charset=UTF-8"};

    public void insertVare(Vare vare) {
        asyncAdapter.setMethod(POST);
        asyncAdapter.setVare(vare);
        asyncAdapter.execute();
    }

    public void updateVare(Vare v) {
        asyncAdapter.setMethod(PUT);
        asyncAdapter.setVareNr(v.getVareNummer());
        asyncAdapter.setVare(v);
        asyncAdapter.execute();
    }

    public void deleteVare(String vareNr) {
        asyncAdapter.setMethod(DELETE);
        asyncAdapter.setVareNr(vareNr);
        asyncAdapter.execute();
    }

    private class VareAdapter extends AsyncTask<String, String, Long> {
        private String HTTPRMethod;
        private Vare v;
        private String vareNr;

        void setVare(Vare vare) {
            v = vare;
        }

        void setVareNr(String vareNr) {
            this.vareNr = vareNr;
        }

        public void setMethod(Type t) {
            HTTPRMethod = s(t);
        }

        @Override
        protected void onPostExecute(Long result) {
            Log.d("HTTP ", String.format("result of %s: %s", HTTPRMethod, result));
            if (result == 0l) {

            } else {
                // fail
            }
        }

        private boolean vnrRequired() {
            return (HTTPRMethod.equals(s(PUT)) || HTTPRMethod.equals(s(DELETE)));
        }

        private boolean sendData() {
            return (HTTPRMethod.equals(s(PUT)) || HTTPRMethod.equals(s(POST)));
        }

        private URL getUri(boolean varenr) {
            try {
                if (varenr) {
                    return new URL(String.format("%s/%s", uri, vareNr));
                }
                return new URL(uri);
            } catch (Exception e) {
                Log.d("[RestAdapter]", "Failed creating URL");
                return null;
            }
        }

        @Override
        protected Long doInBackground(String... params) {
            URL vURL = getUri(vnrRequired());
            if (vURL == null) return -1L;

            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) vURL.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod(HTTPRMethod);
                if (sendData()) {
                    connection.setDoOutput(true);
                    connection.setChunkedStreamingMode(0);
                    connection.setRequestProperty(CONTENT_TYPE[0], CONTENT_TYPE[1]);
                }
                connection.connect();
                if (sendData()) {
                    JSONObject jvare = v.toJSONObject();
                    // with-open (auto close)
                    try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
                        out.write(jvare.toString());
                    }
                }
                int status = connection.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        String responseString;
                        StringBuilder sb = new StringBuilder();
                        while ((responseString = reader.readLine()) != null) {
                            sb.append(responseString);
                        }
                        responseString = sb.toString();
                        // hvis oppdateringen g[r bra returnerer api.php 0, ellers 1.
                        if (responseString.equals("1")) return 1L;
                    }
                } else {
                    return 1l;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("[VareAdapter]", "Exception thrown");
                return 1l;
            } finally {
                if (connection != null)
                    connection.disconnect();
            }
            return 0l;
        }
    }

}
