package com.imovil.uo237484.playasdeasturias.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.imovil.uo237484.playasdeasturias.R;
import com.imovil.uo237484.playasdeasturias.fragments.MiAdaptadorDatos;
import com.imovil.uo237484.playasdeasturias.actividades.PlayaActivity;
import com.imovil.uo237484.playasdeasturias.datos.Filtrar;
import com.imovil.uo237484.playasdeasturias.datos.Playa;
import com.imovil.uo237484.playasdeasturias.datos.TratarDatos;

import java.util.ArrayList;

public class ListAllActivity extends AppCompatActivity {

    ArrayList<Playa> listaplayas;
    private RecyclerView listaplayasrecyclerview;
   /* private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager layoutManager;*/

    Filtrar filtrado;
    TratarDatos datos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        filtrado = new Filtrar();

        datos = new TratarDatos(this);
        listaplayas = datos.getPlayas();
        listaplayasrecyclerview = (RecyclerView) findViewById(R.id.recyclerview_listatodasplayas);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        listaplayasrecyclerview.setLayoutManager(mLayoutManager);

        listaplayasrecyclerview.setAdapter(new MiAdaptadorDatos(listaplayas));

        super.onCreate(savedInstanceState);

       /* listaplayas = new ArrayList<Playa>();
        listaplayasrecyclerview = (RecyclerView) findViewById(R.id.recyclerview_listatodasplayas);

        //para que sea como un listview
        listaplayasrecyclerview.setLayoutManager(new LinearLayoutManager(this));

      //  listarTodasPlayas();

        MiAdaptadorDatos adaptador = new MiAdaptadorDatos(listaplayas);
        listaplayasrecyclerview.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final Intent intent = new Intent(ListAllActivity.this,PlayaActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(),"Selección: "
                        + listaplayas.get(listaplayasrecyclerview.getChildAdapterPosition(view)).getNombre(),Toast.LENGTH_SHORT).show();
            }

        });

        listaplayasrecyclerview.setAdapter(adaptador);

        super.onCreate(savedInstanceState);*/
    }

        /*private void listarTodasPlayas() {
            ArrayList<Playa> listarTodas = null;
            ArrayList<Playa> playas = (ArrayList<Playa>) datos.getPlayas();
            listarTodas = filtrado.obtenerTodasPlayas(playas);
            datos.especificarLista(listarTodas);
            final Intent intent = new Intent(this, PlayaActivity.class);
            startActivity(intent);
    }*/
}

