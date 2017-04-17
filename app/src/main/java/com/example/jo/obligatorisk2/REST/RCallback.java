package com.example.jo.obligatorisk2.REST;

/**
 * Created by Jo on 16.04.2017.
 *
 * Interface required for callback to handle results from restadapter
 */

public interface RCallback {
    void HandleResult(Type t, String result);
    void HandleError();
}
