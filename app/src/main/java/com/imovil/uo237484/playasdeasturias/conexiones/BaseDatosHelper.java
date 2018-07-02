package com.imovil.uo237484.playasdeasturias.conexiones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatosHelper extends SQLiteOpenHelper {

    /**
     * nombre y version de la base de datos
     */
    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;

    //nombre de la tabla
    public static final String TABLE_FAVORITES = "favorites";
    //nombre de las columnas de la tabla


    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PLAYA = "playa";
    public static final String COLUMN_POSITION = "position";


    //creamos la base de datos

    private static final String DATABASE_CREATE = "create table " + TABLE_FAVORITES
            + "( " + COLUMN_ID + " " +
            "integer primary key autoincrement, " + COLUMN_PLAYA
            + " text not null, "+ COLUMN_POSITION
            + " integer not null );";

    //borramos datos de la base

    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS " + TABLE_FAVORITES;

    //constructor
    public BaseDatosHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       //usamos execSQL para poder ejecutar cualquier sentencia que nos nos devuelva ningun valor
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionVieja, int versionNueva) {
        //usamos execSQL para poder ejecutar cualquier sentencia que nos nos devuelva ningun valor
        db.execSQL(DATABASE_DROP);
        this.onCreate(db);
    }
}
