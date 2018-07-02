package com.imovil.uo237484.playasdeasturias.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.imovil.uo237484.playasdeasturias.R;

public class InformacionAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_app);
        informacionApp();
    }

    private void informacionApp() {
        TextView informacion = (TextView) findViewById(R.id.textViewInformacion);
        informacion.setText("Esta aplicación fue creada para poder acceder a todas las playas facilmente.\n"
                + "Esta aplicación nos proporciona no tener que usar en ningún navegador.\n"
                        + "Podemos acceder a toda la información relacionada de cada playa y su ubicación en el mapa.\n"
                        + " También permite guardar playas como favoritas, o buscarlas segun su zona en el mapa de asturias.\n"
                        + "Esta aplicacíon fue creada por Yaiza Fernández Tronco"
                    );
    }
}
