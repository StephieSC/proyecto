package com.example.stephie.proyectomultimedios.Connections;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.stephie.proyectomultimedios.Models.Datos;
import com.example.stephie.proyectomultimedios.Models.Students;
import com.example.stephie.proyectomultimedios.Presenter.MainPresenter;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

/**
 * Created by neo_free on 05/12/2016.
 */

public class MyAsyncTaskExecutor {

    //private RecyclerView.Adapter mAdapter;

    private static MyAsyncTaskExecutor instance;
    private Context ctx;
    private Datos datos;
    private Students student;
    public static MyAsyncTaskExecutor getInstance() {
        if (instance == null) {
            instance = new MyAsyncTaskExecutor();
        }
        return instance;
    }

    public void executeMyAsynctask(final Context ctx, final MainPresenter presenter){
        this.ctx = ctx;
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected String doInBackground(Void... params) {

                String resultado = new HttpServerConnection().connectToServer("http://www.mocky.io/v2/584746043f00004319fe6997", 15000);

                return resultado;
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    //System.out.println(result);
                    student = presenter.getStudent(result);
                    Log.d(TAG, "onPostExecute: "+student.getRol());
                    presenter.registroGCM(student);
                }
            }
        };

        task.execute();
    }




    public void executeMyAsynctask(final Context ctx, final MainPresenter presenter, final TextView al_disponibles, final TextView al_utilizados, final TextView al_totales, final TextView ce_disponibles, final TextView ce_utilizados, final TextView ce_totales){
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

                }
            }
        };

        task.execute();

    }
}

