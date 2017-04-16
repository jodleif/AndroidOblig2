package com.example.jo.obligatorisk2.DataModell;

import org.json.JSONException;
import org.json.JSONObject;
import static com.example.jo.obligatorisk2.DataModell.VareTabell.*;


/**
 * Created by Jo on 16.04.2017.
 */

public class Vare {
    // Felter
    private String vareNummer;
    private String begegnelse;
    private double pris;
    private int katnr;
    private int antall;
    private String hylle;

    public Vare()
    {
        throw new UnsupportedOperationException();
    }

    public Vare(String vareNummer, String begegnelse, double pris, int katnr, int antall, String hylle) {
        this.vareNummer = vareNummer;
        this.begegnelse = begegnelse;
        this.pris = pris;
        this.katnr = katnr;
        this.antall = antall;
        this.hylle = hylle;
    }

    public JSONObject toJSONObject()
    {
        JSONObject vare = new JSONObject();
        try {
            vare.put(VARENUMMER.toString(), this.vareNummer);
            vare.put(BETEGNELSE.toString(), this.begegnelse);
            vare.put(PRIS.toString(), this.pris);
            vare.put(KATNR.toString(), this.katnr);
            vare.put(ANTALL.toString(), this.antall);
            vare.put(HYLLE.toString(), this.hylle);
        } catch (JSONException e) {
            System.err.println("Failed JSON parse");
            return null;
        }
        return vare;
    }
}
