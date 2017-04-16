package com.example.jo.obligatorisk2.REST;

/**
 * Created by Jo on 16.04.2017.
 */

public enum Type {
    GET,
    PUT,
    POST,
    DELETE;
    private static final String[] vv = {"GET","PUT","POST","DELETE"};
    private String value;
    static {
        Type[] v  = Type.values();
        int i = 0;
        for(Type tt : v) {
            tt.value = vv[i++];
        }
    }
    public static String s(Type t) {
        return t.value;
    }
}
