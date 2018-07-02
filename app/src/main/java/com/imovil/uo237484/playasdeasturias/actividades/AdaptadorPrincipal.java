package com.imovil.uo237484.playasdeasturias.actividades;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.imovil.uo237484.playasdeasturias.R;
import com.imovil.uo237484.playasdeasturias.datos.Playa;

import java.util.ArrayList;

public class AdaptadorPrincipal extends BaseAdapter {

    private Activity activity;
    private ArrayList<Playa> playas;
    private Context context;

    public AdaptadorPrincipal(Activity principal, ArrayList<Playa> playas, Context applicationContext) {
        this.activity = principal;
        this.playas = playas;
        this.context= applicationContext;
    }

    public void clear(){
        playas.clear();
    }

    public void addAll(ArrayList<Playa>p){
        for (int i=0;i<p.size();i++){
            playas.add(p.get(i));
        }
    }

    @Override
    public int getCount() {
        return playas.size();
    }

    @Override
    public Object getItem(int position) {
        return playas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_playas_principal, null);
        }
        Playa p = playas.get(position);


        ImageView imagen = (ImageView) convertView.findViewById(R.id.imagenPlayaLista);
        String fichero=p.getImagen();
        String recurso="drawable";
        int res_imagen = context.getResources().getIdentifier(fichero, recurso, context.getPackageName());
        imagen.setImageResource(res_imagen);
        TextView nombre = (TextView) convertView.findViewById(R.id.nombrePlayaLista);
        nombre.setText(p.getNombre());

        return convertView;
    }
}
