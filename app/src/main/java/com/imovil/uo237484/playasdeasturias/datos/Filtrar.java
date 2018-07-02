package com.imovil.uo237484.playasdeasturias.datos;

import android.util.Log;

import com.imovil.uo237484.playasdeasturias.conexiones.BaseDatosFavoritos;

import java.util.ArrayList;

public class Filtrar {


    /**
     * Obtiene el nombre de todas las playas
     * @param playas, lista de playas
     * @return playasFiltradas, nombre de todas las playas
     */
   /* public ArrayList<Playa> obtenerTodasPlayas(ArrayList<Playa> playas) {
        ArrayList<Playa> todaslasplayas = new ArrayList<Playa>();
        for(Playa ptemp : playas){
            todaslasplayas.add(ptemp);
        }
        return todaslasplayas;
    }*/



    //ptemp: variable playas temporal

    /**
     * obtienes las playas de una sola zona
     * @param playas, arraylist
     * @param zona, string
     * @return playasFiltradas, arraylist
     */
    public ArrayList<Playa> filtrarPorZona(ArrayList<Playa> playas, String zona) {
        ArrayList<Playa> playasFiltradasZona = new ArrayList<Playa>();
        for (Playa ptemp : playas) {
            if (ptemp.getZona().contains(zona)) {
                playasFiltradasZona.add(ptemp);
            }
        }
        return playasFiltradasZona;
    }

    /**
     * buscar una playa por su nombre
     * @param playas
     * @param nombre
     * @return playa buscada
     */
    public Playa obtenerPorNombre(ArrayList<Playa> playas, String nombre) {

        for (Playa ptemp : playas){
            if(ptemp.getNombre().equals(nombre)){
                return ptemp;
            }
        }
        return null;
    }

    /**
     * obtiene las playas que se tienen como favoritas
     * @param todaslasplayas, arraylist
     * @param bdFav, basedatos
     * @return playasFavoritas, arraylist
     */
    public ArrayList<Playa> getFavoritas(ArrayList<Playa> todaslasplayas, BaseDatosFavoritos bdFav) {
        ArrayList<String> posicioneslistaplaya = bdFav.getTodasPlayasFavoritas();
        ArrayList<Playa> playasFavoritas = new ArrayList<Playa>();

        for (int i = 0; i < posicioneslistaplaya.size(); i++) {
            for (int j = 0; i < todaslasplayas.size(); j++) {
                Log.d("SSP", posicioneslistaplaya.get(i));
                Log.d("SSP", todaslasplayas.get(j).getNombre());
                if (posicioneslistaplaya.get(i).equals(todaslasplayas.get(j).getNombre())) {
                    playasFavoritas.add(todaslasplayas.get(j));
                }
            }
        }
        return playasFavoritas;
    }
}
