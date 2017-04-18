package com.example.jo.obligatorisk2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jo.obligatorisk2.DataModell.Kategori;
import com.example.jo.obligatorisk2.DataModell.Vare;
import com.example.jo.obligatorisk2.REST.RestAdapter;

import java.util.ArrayList;

public class VareDetail extends AppCompatActivity {
    RestAdapter restAdapter = new RestAdapter();
    Spinner spinner;

    String vnr;
    EditText beskrivelse;
    EditText antall;
    EditText pris;
    EditText hylle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vare_detail);
        spinner = (Spinner) findViewById(R.id.kat_e_gori);
        pris = (EditText) findViewById(R.id.PrisEdit);
        beskrivelse = (EditText)  findViewById(R.id.Beskrivelse);
        antall = (EditText)  findViewById(R.id.antall);
        hylle = (EditText) findViewById(R.id.Hylle);
        setUpSpinner();
        tryToSetUpFromVare(getIntent());
    }

    private boolean tryToSetUpFromVare(Intent i)
    {
        Vare v =  (Vare)i.getSerializableExtra("Vare");
        if(v == null) return false;
        // kategori
        int katPos = v.getKatnr()-1;
        if(katPos < 0 || katPos > spinner.getAdapter().getCount()) throw new IllegalArgumentException("[VareDetail]Index out of bounds");
        vnr = v.getVareNummer();
        spinner.setSelection(katPos);
        antall.setText(String.valueOf(v.getAntall()));
        beskrivelse.setText(v.getBetegnelse());
        pris.setText(String.valueOf(v.getPris()));
        hylle.setText(v.getHylle());
        return true;
    }

    private void setUpSpinner()
    {
        ArrayList<String> items = new ArrayList<>();
        for(Kategori k : Kategori.values()) items.add(String.format("%d | %s", k.kategori(),k.displayName()));
        ArrayAdapter<String> kategorier = new ArrayAdapter<String>(this,  R.layout.support_simple_spinner_dropdown_item, items);
        spinner.setAdapter(kategorier);
    }

}
