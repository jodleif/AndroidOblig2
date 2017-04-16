package com.example.jo.obligatorisk2.DataModell;

/**
 * Created by Jo on 16.04.2017.
 */

public enum VareTabell {
    TABELL_NAVN,
    VARENUMMER,
    BETEGNELSE,
    PRIS,
    KATNR,
    ANTALL,
    HYLLE;

    public static final String[] Desc =
                   {"Vare",
                    "VNr",
                    "Betegnelse",
                    "Pris",
                    "KatNr",
                    "Antall",
                    "Hylle"};

    private String value;

    static {
        VareTabell[] vv = VareTabell.values();
        int i = 0;
        for(VareTabell v : vv) {
            v.value = Desc[i++];
        }
        /*
        TABELL_NAVN.value = Desc[0];
        VARENUMMER.value = Desc[1];
        BETEGNELSE.value = Desc[2];
        PRIS.value = Desc[3];
        KATNR.value = Desc[4];
        ANTALL.value = Desc[5];
        HYLLE.value = Desc[6];
        */
    }

    public String toString() {
        return value;
    }
}
