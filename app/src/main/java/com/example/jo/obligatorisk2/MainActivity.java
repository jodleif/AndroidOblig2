package com.example.jo.obligatorisk2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jo.obligatorisk2.DataModell.Vare;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vare v = new Vare("123","Test beskrivelse", 123.123, 1, 10, "1A");
        JSONObject vare = v.toJSONObject();
        System.out.println(vare);
        setContentView(R.layout.activity_main);
    }
}
