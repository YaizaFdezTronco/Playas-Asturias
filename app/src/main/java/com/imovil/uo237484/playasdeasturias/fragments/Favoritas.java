package com.imovil.uo237484.playasdeasturias.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.imovil.uo237484.playasdeasturias.R;
import com.imovil.uo237484.playasdeasturias.datos.Playa;

import java.util.ArrayList;

public class Favoritas extends AppCompatActivity {

    private static ArrayList<Playa> lista;

    final static String ARG_POSITION="position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_playa);

        if(findViewById(R.id.fragment_playa) != null){
            if(savedInstanceState != null) {
                return;
            }
        }
    }

}