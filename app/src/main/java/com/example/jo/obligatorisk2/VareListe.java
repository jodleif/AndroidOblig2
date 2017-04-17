package com.example.jo.obligatorisk2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jo.obligatorisk2.DataModell.Vare;
import com.example.jo.obligatorisk2.REST.RCallback;
import com.example.jo.obligatorisk2.REST.RestAdapter;
import com.example.jo.obligatorisk2.REST.Type;

import java.util.ArrayList;

import static com.example.jo.obligatorisk2.REST.Type.GET;

public class VareListe extends AppCompatActivity implements RCallback {
    private ArrayList<Vare> varer = new ArrayList<Vare>();
    private ArrayAdapter<Vare> vareAdapter = null;
    private ListView VareListen;
    private RestAdapter restDbAdapter = new RestAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vare_liste);
        VareListen = (ListView) findViewById(R.id.vare_liste);
        //if(isOnline()) {
            restDbAdapter.getVarer();
        //}

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                KategoriFragment fragment = new KategoriFragment();
                fm.beginTransaction().add(fragment, "filter").commit();
            }
        });
    }

    public void updateVareListe(ArrayList<Vare> varer)
    {
        vareAdapter = new ArrayAdapter<Vare>(this, android.R.layout.simple_list_item_1, varer);
        VareListen.setAdapter(vareAdapter);
    }
    @Override
    public void HandleResult(Type t, String result) {
        if(t == GET) {
            varer = Vare.vareListe(result);
            updateVareListe(varer);
        }
    }

    @Override
    public void HandleError() {
        // on error
    }
}
