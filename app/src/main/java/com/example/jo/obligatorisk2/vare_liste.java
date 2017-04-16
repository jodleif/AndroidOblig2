package com.example.jo.obligatorisk2;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class vare_liste extends Activity {
    final public static String URI = "http://itfag.usn.no/~211629/api.php/Vare?order=Betegnelse,asc";
    private ArrayList<Vare> varer = new ArrayList<Vare>();
    private ArrayAdapter<Vare> vareAdapter = null;
    private ListView VareListen;
    private RestDbAdapter restDbAdapter = new RestDbAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vare_liste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
