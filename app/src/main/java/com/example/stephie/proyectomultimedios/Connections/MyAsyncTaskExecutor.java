package com.example.stephie.proyectomultimedios.Connections;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.stephie.proyectomultimedios.Datos;
import com.example.stephie.proyectomultimedios.MainBotonCenaActivity;
import com.example.stephie.proyectomultimedios.MainBotonLunchActivity;
import com.example.stephie.proyectomultimedios.Presenter.MainPresenter;
import com.example.stephie.proyectomultimedios.SinBecaActivity;

/**
 * Created by neo_free on 05/12/2016.
 */

public class MyAsyncTaskExecutor {

    //private RecyclerView.Adapter mAdapter;

    private static MyAsyncTaskExecutor instance;
    private Context ctx;
    private Datos datos;
    public static MyAsyncTaskExecutor getInstance() {
        if (instance == null) {
            instance = new MyAsyncTaskExecutor();
        }
        return instance;
    }

    public Datos executeMyAsynctask(final Context ctx, final MainPresenter presenter, final TextView al_disponibles, final TextView al_utilizados, final TextView al_totales, final TextView ce_disponibles, final TextView ce_utilizados, final TextView ce_totales, final Button al_detalles,final Button ce_detalles){
        this.ctx = ctx;
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected String doInBackground(Void... params) {
                //Toast.makeText(ctx,
                 //       "pre coneccion ", Toast.LENGTH_LONG).show();
                String resultado = new HttpServerConnection().connectToServer("http://www.mocky.io/v2/57f56f722500006e1f134987", 15000);
                //Toast.makeText(ctx,
                 //       "pos coneccion ", Toast.LENGTH_LONG).show();
                return resultado;
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    //System.out.println(result);
                    datos = presenter.getDatos(result);
                    al_disponibles.setText("Disponibles: "+datos.getDisponiblesAL());
                    al_totales.setText("Totales: "+datos.getTotalAL());
                    al_utilizados.setText("Utilizados: "+datos.getUtilizadosAL());
                    ce_disponibles.setText("Disponibles: "+datos.getDisponiblesCE());
                    ce_totales.setText("Totales: "+datos.getTotalCE());
                    ce_utilizados.setText("Utilizados: "+datos.getUtilizadosCE());

                    al_detalles.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(datos.getTotalAL().equals("0")){
                                Intent i = new Intent(ctx, SinBecaActivity.class);
                                ctx.startActivity(i);
                            }else{
                            Intent i = new Intent(ctx, MainBotonLunchActivity.class);
                            i.putExtra("detalles","1");
                            ctx.startActivity(i);
                            }
                    }});
                    ce_detalles.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(datos.getTotalCE().equals("0")){
                                Intent i = new Intent(ctx, SinBecaActivity.class);
                                ctx.startActivity(i);
                            }else{
                            Intent i = new Intent(ctx, MainBotonCenaActivity.class);
                            ctx.startActivity(i);
                        }
                    }});

                }
            }
        };

        task.execute();

        return datos;
    }
}

