package com.example.jo.obligatorisk2.DataModell;

import org.junit.Test;

import static com.example.jo.obligatorisk2.DataModell.VareTabell.*;
import static org.junit.Assert.*;

/**
 * Created by Jo on 16.04.2017.
 */
public class VareTabellTest {

    @Test
    public void toStringTest() throws Exception {
        assertEquals(TABELL_NAVN.toString(), "Vare");
        assertEquals(VARENUMMER.toString(), "VNr");
        assertEquals(BETEGNELSE.toString(), "Betegnelse");
        assertEquals(PRIS.toString(), "Pris");
        assertEquals(KATNR.toString(), "KatNr");
        assertEquals(ANTALL.toString(), "Antall");
        assertEquals(HYLLE.toString(), "Hylle");
    }

}