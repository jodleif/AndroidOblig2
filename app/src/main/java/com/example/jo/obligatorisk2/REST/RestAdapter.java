package com.example.jo.obligatorisk2.REST;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jo.obligatorisk2.AConfig;

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

    private JSONAdapter asyncAdapter = new JSONAdapter();
    private String uri = AConfig.APP_URI + "/Vare";
    private static final String[] CONTENT_TYPE = {"content-type", "application/json; charset=UTF-8"};
    RCallback cb;

    public RestAdapter() {
        // default
    }

    public RestAdapter(String URI)
    {
        this(URI, null);
    }

    public RestAdapter(RCallback cb)
    {
        this.cb = cb;
    }

    public RestAdapter(String URI, RCallback cb)
    {
        this.cb = cb;
        this.uri = URI;
    }

    public void getVarer()
    {
        // task can only be executed once
        asyncAdapter = new JSONAdapter();
        asyncAdapter.setMethod(GET);
        asyncAdapter.execute();
    }
    public void insertVare(JSONObject vare) {
        asyncAdapter = new JSONAdapter();
        asyncAdapter.setMethod(POST);
        asyncAdapter.setVare(vare);
        asyncAdapter.execute();
    }

    public void updateVare(JSONObject v, String key) {
        asyncAdapter = new JSONAdapter();
        asyncAdapter.setMethod(PUT);
        asyncAdapter.setVareNr(key);
        asyncAdapter.setVare(v);
        asyncAdapter.execute();
    }

    public void deleteVare(String vareNr) {
        asyncAdapter = new JSONAdapter();
        asyncAdapter.setMethod(DELETE);
        asyncAdapter.setVareNr(vareNr);
        asyncAdapter.execute();
    }

    private class JSONAdapter extends AsyncTask<String, String, Long> {
        private Type HTTPRMethod;
        private JSONObject v;
        private String vareNr;
        private String response;

        void setVare(JSONObject vare) {
            v = vare;
        }

        void setVareNr(String vareNr) {
            this.vareNr = vareNr;
        }

        public void setMethod(Type t) {
            HTTPRMethod = t;
        }

        @Override
        protected void onPostExecute(Long result) {
            Log.d("HTTP ", String.format("result of %s: %s", HTTPRMethod, result));

            if (result == 0l) {
                if(cb != null) {
                    cb.HandleResult(HTTPRMethod, response);
                }
            } else {
                // fail
                if(cb != null) {
                    cb.HandleError();
                }
            }
        }

        private boolean vnrRequired() {
            return (HTTPRMethod.equals(PUT) || HTTPRMethod.equals(DELETE));
        }

        private boolean sendData() {
            return (HTTPRMethod.equals(PUT) || HTTPRMethod.equals(POST));
        }

        private URL getUri(boolean varenr) {
            try {
                if (varenr) {
                    return new URL(String.format("%s/%s", uri, vareNr));
                }
                //JSON formattert output
                if(HTTPRMethod == GET) {
                    return new URL(String.format("%s?Order=betegnelse,asc&transform=1",uri));
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
            // clear response
            response = null;

            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) vURL.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod(s(HTTPRMethod));
                if (sendData()) {
                    connection.setDoOutput(true);
                    connection.setChunkedStreamingMode(0);
                    connection.setRequestProperty(CONTENT_TYPE[0], CONTENT_TYPE[1]);
                }
                connection.connect();
                if (sendData()) {
                    // with-open (auto close)
                    try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
                        out.write(v.toString());
                    }
                }
                int status = connection.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        StringBuilder sb = new StringBuilder();
                        while ((response = reader.readLine()) != null) {
                            sb.append(response);
                        }
                        response = sb.toString();
                        if (HTTPRMethod == GET && response.length() != 0) return 0L;
                        // hvis oppdateringen g[r bra returnerer api.php 0, ellers 1.
                        if (response.equals("0")) return 1L;
                    }
                } else {
                    return 1l;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("[JSONAdapter]", "Exception thrown");
                return 1l;
            } finally {
                if (connection != null)
                    connection.disconnect();
            }
            return 0l;
        }
    }

}
