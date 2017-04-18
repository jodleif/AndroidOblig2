package com.example.jo.obligatorisk2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jo.obligatorisk2.DataModell.Kategori;
import com.example.jo.obligatorisk2.DataModell.Vare;
import com.example.jo.obligatorisk2.REST.RCallback;
import com.example.jo.obligatorisk2.REST.RestAdapter;
import com.example.jo.obligatorisk2.REST.Type;

import java.util.ArrayList;

/**
 * En aktivitet som forventer en intent som inneholder en vare.
 * Denne aktiviteten viser detaljert informasjon om varen,
 * og ved et trykk p[ floatingactionbutton gjøres et REST
 * kall til endepunktet med oppdatert informasjon
 */
public class VareDetail extends AppCompatActivity implements RCallback{
    RestAdapter restAdapter = new RestAdapter(this);
    Spinner spinner;

    String vnr;
    Vare vare;
    EditText beskrivelse;
    EditText antall;
    EditText pris;
    EditText hylle;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vare_detail);
        hookControls();
        setUpSpinner();
        setUpSendButton();
        tryToSetUpFromVare(getIntent());
    }

    private void hookControls()
    {
        spinner = (Spinner) findViewById(R.id.kat_e_gori);
        pris = (EditText) findViewById(R.id.PrisEdit);
        beskrivelse = (EditText)  findViewById(R.id.Beskrivelse);
        antall = (EditText)  findViewById(R.id.antall);
        hylle = (EditText) findViewById(R.id.Hylle);
        fab = (FloatingActionButton) findViewById(R.id.fab_detail);
    }
    private boolean tryToSetUpFromVare(Intent i)
    {
        vare =  (Vare)i.getSerializableExtra("Vare");
        if(vare == null) return false;
        // kategori
        int katPos = vare.getKatnr()-1;
        if(katPos < 0 || katPos > spinner.getAdapter().getCount()) throw new IllegalArgumentException("[VareDetail]Index out of bounds");
        vnr = vare.getVareNummer();
        spinner.setSelection(katPos);
        antall.setText(String.valueOf(vare.getAntall()));
        beskrivelse.setText(vare.getBetegnelse());
        pris.setText(String.valueOf(vare.getPris()));
        hylle.setText(vare.getHylle());
        return true;
    }

    private void setUpSendButton()
    {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityToVare()) {
                    restAdapter.updateVare(vare.toJSONObject(), vnr);
                }
            }
        });
    }

    private boolean activityToVare()
    {
        if (vare == null) return false;
        vare.setAntall(Integer.valueOf(antall.getText().toString()));
        vare.setPris(Double.valueOf(pris.getText().toString()));
        vare.setBetegnelse(beskrivelse.getText().toString());
        vare.setHylle(hylle.getText().toString());
        //Kategori nummer starter fra 1
        vare.setKatnr(spinner.getSelectedItemPosition()+1);
        return true;
    }


    private void setUpSpinner()
    {
        ArrayList<String> items = new ArrayList<>();
        for(Kategori k : Kategori.values()) items.add(String.format("%d | %s", k.kategori(),k.displayName()));
        ArrayAdapter<String> kategorier = new ArrayAdapter<String>(this,  R.layout.support_simple_spinner_dropdown_item, items);
        spinner.setAdapter(kategorier);
    }

    @Override
    public void HandleResult(Type t, String result) {
        Snackbar.make(findViewById(R.id.vare_detail), String.format("Oppdatert vare: %s",vnr), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void HandleError() {
        Snackbar.make(findViewById(R.id.vare_detail), String.format("Oppdatering feilet!",vnr), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
