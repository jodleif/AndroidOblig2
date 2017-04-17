package com.example.jo.obligatorisk2.DataModell;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.example.jo.obligatorisk2.DataModell.Kategori.*;

/**
 * Created by Jo on 17.04.2017.
 */
public class KategoriTest {
    @Test
    public void kategori() throws Exception {
        Kategori[] arr = Kategori.values();
        assertEquals(arr.length, 21);
        assertEquals(DEKORASJONER.kategori(), 17);
        assertEquals(s(DEKORASJONER).equals("Dekorasjoner"),true);
    }

}