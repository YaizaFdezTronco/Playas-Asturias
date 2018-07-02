package com.imovil.uo237484.playasdeasturias.conexiones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class BaseDatosFavoritos {

    //referencia para manejar la base de datos.Nos lo proporciona MyDBHelper y nos da sus operaciones
    private SQLiteDatabase database;
    //referencia para crear y actualizar la base con el helper
    private BaseDatosHelper dbHelper;

    //creamos las columnas de las tablas (0,1,2..)
    private final String[] todasLasColumnas = {BaseDatosHelper.COLUMN_ID, BaseDatosHelper.COLUMN_PLAYA, BaseDatosHelper.COLUMN_POSITION};

    //constructor
    public BaseDatosFavoritos(Context context) {
        dbHelper = new BaseDatosHelper(context);
    }

    /**
     * metodo que abre la base
     */
    public void open() {
        //con este metodo abrimos la base con una conexion de escritura
        database = dbHelper.getWritableDatabase();
    }

    /**
     * metodo que cierra la base de datos
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * añadir playa a favoritos
     * @param nombre, i
     * @returm playaInsertada
     */
    public long añadirFavoritos(String nombre, int i) {
        //asignar valores a las columnas
        ContentValues valor= new ContentValues();
        valor.put(BaseDatosHelper.COLUMN_ID, nombre);
        valor.put(BaseDatosHelper.COLUMN_POSITION, i);
        // insertar playa
        long playaInsertada=database.insert(BaseDatosHelper.TABLE_FAVORITES,null,valor);
        return  playaInsertada;
    }

    /**
     * borrar playa de favoritos
     * @param i, posicion de la playa
     * @return playaEliminada
     */
    public long eliminarFavoritos(int i){
        //eliminamos playa
        long playaEliminada=database.delete(BaseDatosHelper.TABLE_FAVORITES, BaseDatosHelper.COLUMN_POSITION+"="+i,null);
        return playaEliminada;
    }

    /**
     * obtienes la lista de todas las playas favoritas
     * @return listaDeFavoritas
     */
    public ArrayList<String> getTodasPlayasFavoritas() {
        //lista de favoritas ( resultados)
        ArrayList<String> listaDeFavoritas= new ArrayList<String>();
        //creamos un cursor (señala a la posicion -1)
        Cursor cursor= database.query(BaseDatosHelper.TABLE_FAVORITES,todasLasColumnas,null,null,null,null, null);

        //mover el cursor al primer dato
        cursor.moveToFirst();

        //comprobar que el cursor no esta en el ultimo dato
        while (!cursor.isAfterLast()){
            //crear una variable String que te devuelve el valor de la primera columna(nombre)
            final String favorita= cursor.getString(1);

            //añadimos el valor a la lista de los resultados
            listaDeFavoritas.add(favorita);
            //movemos el cursor al siguiente dato
            cursor.moveToNext();
        }

        //cerramos el cursor
        cursor.close();

        //devolvemos la lista de resultados
        return listaDeFavoritas;
    }
}
