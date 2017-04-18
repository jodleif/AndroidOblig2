package com.example.jo.obligatorisk2.DataModell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.ArrayList;

import static com.example.jo.obligatorisk2.DataModell.VareTabell.*;


/**
 * Created by Jo on 16.04.2017.
 *
 * Implementerer serializable slik at den kan sendes med en intent
 */

public class Vare implements Serializable {
    // Felter
    private String vareNummer;
    private String betegnelse;
    private double pris;
    private int katnr;
    private int antall;
    private String hylle;

    public Vare()
    {
        throw new UnsupportedOperationException();
    }

    public Vare(String vareNummer, String betegnelse, double pris, int katnr, int antall, String hylle) {
        this.vareNummer = vareNummer;
        this.betegnelse = betegnelse;
        this.pris = pris;
        this.katnr = katnr;
        this.antall = antall;
        this.hylle = hylle;
    }

    public Vare(JSONObject vare)
    {
       fromJSON(vare);
    }

    public Vare(String JSONVare) throws InvalidObjectException
    {
        try {
            JSONObject vare = new JSONObject(JSONVare);
            fromJSON(vare);
        } catch (JSONException e) {
            throw new InvalidObjectException("Failed JSON parse");
        }
    }

    private void fromJSON(JSONObject vare)
    {
        vareNummer = vare.optString(VARENUMMER.toString());
        betegnelse = vare.optString(BETEGNELSE.toString());
        pris = vare.optDouble(PRIS.toString());
        katnr = vare.optInt(KATNR.toString());
        antall = vare.optInt(ANTALL.toString());
        hylle = vare.optString(HYLLE.toString());
    }

    public static ArrayList<Vare> vareListe(String vareListe)
    {
        ArrayList<Vare> varer = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(vareListe);
            JSONArray parseVarer = data.optJSONArray(TABELL_NAVN.toString());

            for (int i = 0; i < parseVarer.length();++i) {
                varer.add(new Vare(parseVarer.getJSONObject(i)));
            }
        } catch (JSONException e) {
            System.err.println("Error deserializing JSON-array");
        }
        return varer;
    }

    public JSONObject toJSONObject()
    {
        JSONObject vare = new JSONObject();
        try {
            vare.put(VARENUMMER.toString(), this.vareNummer);
            vare.put(BETEGNELSE.toString(), this.betegnelse);
            vare.put(PRIS.toString(), this.pris);
            vare.put(KATNR.toString(), this.katnr);
            vare.put(ANTALL.toString(), this.antall);
            vare.put(HYLLE.toString(), this.hylle);
        } catch (JSONException e) {
            System.err.println("Failed JSON serialization");
            return null;
        }
        return vare;
    }

    public void incrementPrice()
    {
        pris += 1.0;
    }
    @Override
    public String toString() {
        return String.format("[%s] %s : %f", vareNummer, betegnelse, pris);
    }
    public String toDebugString() {
        return "Vare{" +
                "vareNummer='" + vareNummer + '\'' +
                ", betegnelse='" + betegnelse + '\'' +
                ", pris=" + pris +
                ", katnr=" + katnr +
                ", antall=" + antall +
                ", hylle='" + hylle + '\'' +
                '}';
    }

    public String getVareNummer() {
        return vareNummer;
    }

    public String getBetegnelse() {
        return betegnelse;
    }

    public double getPris() {
        return pris;
    }

    public int getKatnr() {
        return katnr;
    }

    public int getAntall() {
        return antall;
    }

    public String getHylle() {
        return hylle;
    }

    public void setVareNummer(String vareNummer) {
        this.vareNummer = vareNummer;
    }

    public void setBetegnelse(String betegnelse) {
        this.betegnelse = betegnelse;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public void setKatnr(int katnr) {
        this.katnr = katnr;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }

    public void setHylle(String hylle) {
        this.hylle = hylle;
    }
}
