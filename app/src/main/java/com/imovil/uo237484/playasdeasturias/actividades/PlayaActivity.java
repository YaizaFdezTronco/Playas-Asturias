package com.imovil.uo237484.playasdeasturias.actividades;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.imovil.uo237484.playasdeasturias.R;
import com.imovil.uo237484.playasdeasturias.conexiones.BaseDatosFavoritos;
import com.imovil.uo237484.playasdeasturias.datos.Playa;
import com.imovil.uo237484.playasdeasturias.datos.TratarDatos;

import java.util.ArrayList;
import java.util.List;

public class PlayaActivity extends AppCompatActivity {

    private static List<Playa> playas;
    String name;
    private BaseDatosFavoritos bdFav;
    private TratarDatos datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playa);


        final Playa playa = getIntent().getExtras().getParcelable(Principal.OBJETO_KEY);

        TextView nombre = findViewById(R.id.textViewNombrePlaya);
        ImageView imagenPlaya = findViewById(R.id.imagen_playa);
        TextView concejo = findViewById(R.id.textViewNombreConcejo);
        TextView coordenadas = findViewById(R.id.textViewDatosCoordenadas);
        TextView zona = findViewById(R.id.textViewDatosZona);
        TextView informacion = findViewById(R.id.textViewDatosInformacion);
        TextView tipoPlaya = findViewById(R.id.textViewDatoTipoPlaya);
        TextView longitud = findViewById(R.id.textViewDatoLongitud);
        TextView accesos = findViewById(R.id.textViewDatosAcceso);
        TextView servicios = findViewById(R.id.textViewDatosServicios);

        nombre.setText("" + playa.getNombre());
        imagenPlaya.setImageResource(getResources().getIdentifier(playa.getImagen(), "drawable", getPackageName()));
        concejo.setText("" + playa.getConcejo());
        coordenadas.setText("" + playa.getCoordenadas());
        zona.setText("" + playa.getZona());
        informacion.setText("" + playa.getInformacion());
        tipoPlaya.setText("" + playa.getTipodeplaya());
        longitud.setText("" + playa.getLongitud());
        accesos.setText("" + playa.getAccesos());
        servicios.setText("" + playa.getServicios());


        name = playa.getNombre();

        final View Fview = findViewById(R.id.activity_playa_info_scrollview);

        Button favoritos = findViewById(R.id.botonFavoritos);
        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarFavoritos(Fview, playa.getNombre());

            }
        });

        //cambiarBotonFavoritos(Fview);

        Button mapa = findViewById(R.id.botonMapa);
        mapa.setOnClickListener(new View.OnClickListener() {
            //Requiere llamar a la Api nivel 21. necesario en Android Studio
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                verMapa(Fview);
            }
        });


    }

    /**
     * metodo para crear un menu
     * @param menu
     * @return verdadero
     */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_playa,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        else if(id==R.id.menu_informacion){
            final Intent intent = new Intent(PlayaActivity.this,InformacionAppActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.menu_todaslasplayas){
            final Intent intent = new Intent(PlayaActivity.this,Principal.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void cambiarBotonFavoritos(View fview) {

        Button favoritos = (Button) findViewById(R.id.botonFavoritos);
        openBD();
        if (!esFavorita(name)) {
            favoritos.setText("Añadir a favoritos");
        } else {
            favoritos.setText("Eliminar de favoritos");
        }
        closeBD();
    }

    private void actualizarFavoritos(View fview, String nombre) {
        openBD();
        if (!esFavorita(nombre)) {
            bdFav.añadirFavoritos(nombre, posicion(nombre));
            Toast.makeText(getApplicationContext(), "Se ha añadido la playa a favoritos", Toast.LENGTH_SHORT).show();
            cambiarBotonFavoritos(fview);
        } else {
            bdFav.eliminarFavoritos(posicion(nombre));

            Toast.makeText(getApplicationContext(), "Se ha eliminado la playa de favoritos", Toast.LENGTH_SHORT).show();
            cambiarBotonFavoritos(fview);
        }
        closeBD();
    }

    private int posicion(String nombre) {
        for (int i = 0; i < datos.getPlayas().size(); i++) {
            if (datos.getPlayas().get(i).getNombre().equals(name)) {
                return i;
            }
        }
        return 0;
    }


    private boolean esFavorita(String nombre) {
        List<String> favoritas = bdFav.getTodasPlayasFavoritas();
        for (String i : favoritas) {
            if (i.equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    //al comprobar la conexion y en el metodo usar getAllNetworks, en este metodo tambien
    // se necesita la restriccion para usar a partir de la api 21
    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void verMapa(View view) {

        //if (ComprobarConexion()) {
            final Intent mIntent = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(mIntent);
        /*} else {
            Toast.makeText(getApplicationContext(), "No posee conexion a internet", Toast.LENGTH_SHORT).show();
        }*/

    }

    private Context miContext;

    // getAllNetworks se requiere a partir de la api 21, la api 14, que es mi api minima, no lo acepta.
    //verifica toda las llamadas de la api y advierte sobre cualquier llamada que no este disponible
    //en todas las versiones segmentadas por esta app
    /*@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private boolean ComprobarConexion() {

        boolean conectado = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) miContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        Network[] networks = connectivityManager.getAllNetworks();
        NetworkInfo networkInfo;
        for (Network network : networks) {
            networkInfo = connectivityManager.getNetworkInfo(network);
            if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                conectado = true;
            }
        }
        Toast.makeText(getApplicationContext(), "no posee conexion a internet", Toast.LENGTH_SHORT).show();
        return conectado;
    }
*/

    //metodos de la base de datos

    public void openBD() {
        bdFav = new BaseDatosFavoritos(getApplicationContext());
        bdFav.open();
    }

    public void closeBD() {
        bdFav.close();
    }

   /* public static List<Playa> getPlayas() {
        return playas;
    }}*/
}
