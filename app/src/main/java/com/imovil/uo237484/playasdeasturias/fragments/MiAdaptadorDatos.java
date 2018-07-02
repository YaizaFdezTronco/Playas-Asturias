package com.imovil.uo237484.playasdeasturias.fragments;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imovil.uo237484.playasdeasturias.R;
import com.imovil.uo237484.playasdeasturias.datos.Playa;

import java.util.ArrayList;



public class MiAdaptadorDatos extends RecyclerView.Adapter<MiAdaptadorDatos.Holder> implements View.OnClickListener {


    private ArrayList<Playa> listaplayas;
    private Context context;
    private View.OnClickListener escuchador;


    public MiAdaptadorDatos(ArrayList<Playa> listaplayas) {
        this.listaplayas = listaplayas;
    }

    public void setOnClickListener(View.OnClickListener escuchador){
        this.escuchador = escuchador;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_playas_principal,null,false);
        vista.setOnClickListener(this);
        return new Holder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        //asignar datos a cada elemento de la lista


        int res_imagen = context.getResources().getIdentifier(listaplayas.get(position).getImagen(), "drawable", context.getPackageName());
        holder.imagen.setImageResource(res_imagen);
        holder.nombre.setText(listaplayas.get(position).getNombre());

    }

    @Override
    public int getItemCount() {
        return listaplayas.size();
    }



    @Override
    public void onClick(View v) {
        if(escuchador != null){
            escuchador.onClick(v);
        }
    }

    public static class Holder extends RecyclerView.ViewHolder{

        ImageView imagen;
        TextView nombre;

        public Holder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagenPlayaLista);
            nombre = (TextView) itemView.findViewById(R.id.nombrePlayaLista);

        }
    }
}
