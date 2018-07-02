package com.imovil.uo237484.playasdeasturias.actividades;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.imovil.uo237484.playasdeasturias.R;
import com.imovil.uo237484.playasdeasturias.conexiones.BaseDatosFavoritos;
import com.imovil.uo237484.playasdeasturias.datos.Filtrar;
import com.imovil.uo237484.playasdeasturias.datos.Playa;
import com.imovil.uo237484.playasdeasturias.datos.TratarDatos;
import com.imovil.uo237484.playasdeasturias.fragments.Favoritas;
import com.imovil.uo237484.playasdeasturias.fragments.ListAllActivity;

import java.util.ArrayList;
import java.util.Random;



public class Principal extends AppCompatActivity {


    private Filtrar filtrado;

    private BaseDatosFavoritos bdFav;

    private TratarDatos datos;
    //private ArrayList<Playa> playas;
    private ListView lista;


    public static String OBJETO_KEY = "OBJETO_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        filtrado = new Filtrar();
        datos = new TratarDatos(this);




        lista = (ListView) findViewById(R.id.ListaPlayasPrincipal);
        ArrayList<Playa> pla= elegirLista_playasAleatorias();
        final AdaptadorPrincipal adaptador = new AdaptadorPrincipal(this,pla,getApplicationContext());

        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Playa playa = (Playa) adaptador.getItem(position);
                Playa playaSeleccionada = filtrado.obtenerPorNombre(datos.getPlayas(), playa.getNombre());

                //actividad de la playa seleccionada
                final Intent intent = new Intent(Principal.this,PlayaActivity.class);
                intent.putExtra("OBJETO_KEY",playaSeleccionada);
                intent.putExtra("POSICION_KEY",position);
                startActivity(intent);
            }
        });

        /*Button botonIrAtodas = (Button) findViewById(R.id.buttonIrATodas);
        botonIrAtodas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Principal.this, ListAllActivity.class);
                startActivity(intent);
            }
        });*/
    }

        private ArrayList<Playa> elegirLista_playasAleatorias(){
            ArrayList<Playa> playasElegidas = new ArrayList<Playa>();
           // Random r = new Random();
            for(int i=0; i<datos.getPlayas().size(); i++){
                /*int valorDado = r.nextInt(datos.getPlayas().size());
                if(playasElegidas.contains(datos.getPlayas().get(valorDado)))
                    i--;
                else*/
                    playasElegidas.add(datos.getPlayas().get(i));
            }
            return playasElegidas;
        }

    /**
     * metodo que muestra todas las playas
     * @param view
     */

   /* public  void todasLasPlayas(View view){
        ArrayList<Playa> listaTodas = null;
        ArrayList<Playa> playas = (ArrayList<Playa>) datos.getPlayas();
        listaTodas=filtrado.obtenerTodasPlayas(playas);
        datos.especificarLista(listaTodas);
        final Intent intent = new Intent(Principal.this, ListAllActivity.class);
        startActivity(intent);

    }*/


    /**
     * metodo que muestra todas las playas
     * @param view
     */

    public  void vertodasLasPlayas(View view){
        final Intent intent = new Intent(Principal.this, ListAllActivity.class);
        startActivity(intent);

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
            final Intent intent = new Intent(Principal.this,InformacionAppActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.menu_favoritos){
            openDB();
            ArrayList<Playa> favoritas = filtrado.getFavoritas(datos.getPlayas(),bdFav);
            if(favoritas.isEmpty()){
                Toast.makeText(getApplicationContext(),"No hay playas favoritas", Toast.LENGTH_SHORT).show();
            }else{
                datos.especificarLista(favoritas);
                final Intent intent = new Intent(Principal.this,Principal.class);
                startActivity(intent);
            }
            closeDB();
        }/*
        else if(id == R.id.menu_todaslasplayas){
            final Intent intent = new Intent(Principal.this,Principal.class);
            startActivity(intent);
        }else if(id==R.id.menu_zona){
            opcionesZonaPlaya(id);
        }*/else if(id == R.id.menu_salir){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * metodo para elegir las playas por zona en el menu
     * @param id
     */
   /* private void opcionesZonaPlaya(int id) {
        ArrayList<Playa> listaNueva=null;
        ArrayList<Playa> playas = datos.getPlayas();

        if(id==R.id.menu_zona_centro){
            listaNueva = filtrado.filtrarPorZona(playas,"centro");
            TratarDatos.especificarLista(listaNueva);
        }else if(id==R.id.menu_zona_oriente){
            listaNueva = filtrado.filtrarPorZona(playas,"oriente");
            TratarDatos.especificarLista(listaNueva);
        }else if(id== R.id.menu_zona_occidente){
            listaNueva = filtrado.filtrarPorZona(playas,"occidente");
            TratarDatos.especificarLista(listaNueva);
        }
    }*/

    /**
     * Método que se conecta a la base de datos
     */
    public void openDB(){
        bdFav = new BaseDatosFavoritos(getApplicationContext());
        bdFav.open();
    }

    /**
     * Método que cierra la base de datos
     */
    public void closeDB(){
        bdFav.close();
    }
}
