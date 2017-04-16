package com.example.jo.obligatorisk2.REST;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jo on 16.04.2017.
 */
public class TypeTest {
    @Test
    public void s() throws Exception {
        assertEquals("GET",Type.s(Type.GET));
        assertEquals("PUT",Type.s(Type.PUT));
        assertEquals("POST",Type.s(Type.POST));
        assertEquals("DELETE",Type.s(Type.DELETE));
    }

}