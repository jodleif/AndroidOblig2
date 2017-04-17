package com.example.jo.obligatorisk2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jo.obligatorisk2.DataModell.Vare;
import com.example.jo.obligatorisk2.REST.Callback;
import com.example.jo.obligatorisk2.REST.RestAdapter;
import com.example.jo.obligatorisk2.REST.RestFetcher;

import java.util.ArrayList;

public class VareListe extends AppCompatActivity implements Callback{
    final public static String URI = "http://itfag.usn.no/~211629/api.php/Vare?order=Betegnelse,asc";
    private ArrayList<Vare> varer = new ArrayList<Vare>();
    private ArrayAdapter<Vare> vareAdapter = null;
    private ListView VareListen;
    private RestAdapter restDbAdapter = new RestAdapter();
    private RestFetcher restFetcher = new RestFetcher(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vare_liste);
        VareListen = (ListView) findViewById(R.id.vare_liste);
        //if(isOnline()) {
            restFetcher.getVareListe();
        //}

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void updateVareListe(ArrayList<Vare> varer)
    {
        vareAdapter = new ArrayAdapter<Vare>(this, android.R.layout.simple_list_item_1, varer);
        VareListen.setAdapter(vareAdapter);
    }
    @Override
    public void HandleResult(String result) {
        varer = Vare.vareListe(result);
        updateVareListe(varer);
    }

    @Override
    public void HandleError() {
        // on error
    }
}
