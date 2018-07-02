package com.imovil.uo237484.playasdeasturias.actividades;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.imovil.uo237484.playasdeasturias.R;
import com.imovil.uo237484.playasdeasturias.datos.Playa;
import com.imovil.uo237484.playasdeasturias.datos.TratarDatos;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TratarDatos datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Cambio el  marker a Oviedo
        LatLng oviedo = new LatLng(43.3579649, -5.8733862);
        CameraPosition cameraPosition = new
                CameraPosition.Builder()
                .target(new LatLng(43.3579649, -5.8733862)) // Centro del mapa
                .zoom((float) 7.5) // Establece nivel de zoom
                .bearing(360) // Orientación cámara al sur
                .tilt(30) // Inclinación cámara 30 grados
                .build();

        mMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(cameraPosition));

        /*mMap.addMarker(new MarkerOptions().position(oviedo).title("Marker in Oviedo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(oviedo));*/

        añadirPlayas();

    }

    private void añadirPlayas() {

        if (datos.nombrePlaya.equals("")) {
            for (Playa p : datos.getPlayas()) {
                double latitud = p.getCoordenadas()[0];
                double longitud = p.getCoordenadas()[1];
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitud, longitud))
                        .title(p.getNombre())
                        .snippet(p.getConcejo() +  p.getLongitud())
                );
            }
        } else {
            for (Playa p : datos.getPlayas()) {
                if (p.getNombre().equals(datos.nombrePlaya)) {
                    double latitud = p.getCoordenadas()[0];
                    double longitud = p.getCoordenadas()[1];
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(latitud, longitud))
                            .title(p.getNombre())
                            .snippet(p.getConcejo() + p.getLongitud())
                    );                }
            }
            datos.nombrePlaya = "";
        }
    }


}
