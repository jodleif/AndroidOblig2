package com.example.jo.obligatorisk2.DataModell;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Jo on 16.04.2017.
 */
public class VareTest {
    private static final String jsons =
            "{\n" +
                    "  \"Vare\": [\n" +
                    "    {\n" +
                    "      \"VNr\": \"10820\",\n" +
                    "      \"Betegnelse\": \"Dukkehår, hvitt\",\n" +
                    "      \"Pris\": \"46.50\",\n" +
                    "      \"KatNr\": \"13\",\n" +
                    "      \"Antall\": \"106\",\n" +
                    "      \"Hylle\": \"E12\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"VNr\": \"10822\",\n" +
                    "      \"Betegnelse\": \"Dukkehår, lys brunt\",\n" +
                    "      \"Pris\": \"46.50\",\n" +
                    "      \"KatNr\": \"13\",\n" +
                    "      \"Antall\": \"0\",\n" +
                    "      \"Hylle\": \"E12\"\n" +
                    "    }]" +
                    "}";
    @Test
    public void vareListe() throws Exception {
        ArrayList<Vare> v = Vare.vareListe(jsons);
        assertEquals(2, v.size());
    }

    @Test
    public void toJSONObject() throws Exception {

    }

}