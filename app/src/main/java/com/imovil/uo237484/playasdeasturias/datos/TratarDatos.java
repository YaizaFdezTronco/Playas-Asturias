package com.imovil.uo237484.playasdeasturias.datos;

import android.app.Activity;
import android.content.Context;

import android.util.JsonReader;

import com.imovil.uo237484.playasdeasturias.R;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.in;

public class TratarDatos{


    private static ArrayList<Playa> playas;

    private Activity activity;
    private Context context;

    public static String nombrePlaya="";

    public ArrayList<String> nombre = new ArrayList<String>();

    public TratarDatos(Context principal) {
        context = principal;
    }


    public void inicialize() {
        try {
            playas = (ArrayList<Playa>) leerJsonStream(context.getResources().openRawResource(R.raw.playas));
        } catch (IOException e) {
            e.printStackTrace();
            throw  new RuntimeException("error");
        }
    }

    public TratarDatos(Activity activity, ArrayList<Playa> playas, Context context){
        this.activity = activity;
        this.playas = playas;
        this.context = context;
    }



    //leer formato Json
    public List<Playa> leerJsonStream(InputStream in) throws IOException {
        //nueva instancia JsonReader
        JsonReader leer= new JsonReader(new InputStreamReader(in,"UTF-8"));
        try {
            //leer el array
            return leerArrayPlayas(leer);
        } finally {
            leer.close();
        }
    }

    private List<Playa> leerArrayPlayas(JsonReader leer) throws IOException{
        //creamos una lista temporal
        ArrayList<Playa> playas= new ArrayList<Playa>();

        //con el metodo beginArray() apuntamos al primer elemento
        leer.beginArray();
        //con un metodo while leemos el siguiente elemento
        while (leer.hasNext()){
            //leemos el objeto
            playas.add(leerPlayas(leer));
        }
        //se cierra el array
        leer.endArray();
        return playas;

    }

    private Playa leerPlayas(JsonReader leer) throws IOException{

        String nombre = null;
        String imagen = null;
        String concejo = null;
        double[] coordenadas  = new double[2];
        String zona = null;
        String informacion = null;
        String tipodeplaya = null;
        String longitud = null;
        String accesos = null;
        String servicios = null;

        //iniciamos el obejor para apuntar al primer dato
        leer.beginObject();

        while (leer.hasNext()){
            String atributo = leer.nextName();
            switch (atributo.toLowerCase()){
                case "nombre":
                    nombre=leer.nextString();
                    break;
                case "imagen":
                    imagen=leer.nextString();
                    break;
                case "concejo":
                    concejo=leer.nextString();
                    break;
                case "coordenadas":
                    String[] aux = leer.nextString().split(",");
                    coordenadas = new double[2];
                    coordenadas[0] = Double.parseDouble(aux[0]);
                    coordenadas[1] =Double.parseDouble(aux[1]);
                    break;
                case "zona":
                    zona=leer.nextString();
                    break;
                case "informacion":
                    informacion=leer.nextString();
                    break;
                case "tipodeplaya":
                    tipodeplaya=leer.nextString();
                    break;
                case "longitud":
                    longitud=leer.nextString();
                    break;
                case "accesos":
                    accesos=leer.nextString();
                    break;
                case "servicios":
                    servicios=leer.nextString();
                    break;
                default:
                    leer.skipValue();
                    break;

            }
        }
        leer.endObject();
        return new Playa(nombre,imagen,concejo,coordenadas,zona,informacion,tipodeplaya,longitud,accesos,servicios);
        }

        public void ordenarPornombre(ArrayList<Playa> playas){
            Collections.sort(playas, new Comparator<Playa>() {
                @Override
                public int compare(Playa p1, Playa p2) {
                    return p1.getNombre().compareTo(p2.getNombre());
                }
            });
        }


        public ArrayList<Playa> getPlayas() {
            if (playas == null)
               inicialize();

            return playas;
        }

    /**
     * especifica que playa son mostradas al usuario
     * @param listaPlayas
     */
    public static void especificarLista(ArrayList<Playa> listaPlayas) {

        playas = listaPlayas;
    }


}
