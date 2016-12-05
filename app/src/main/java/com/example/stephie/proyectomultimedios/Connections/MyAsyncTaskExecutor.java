package com.example.stephie.proyectomultimedios.Connections;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stephie.proyectomultimedios.Datos;
import com.example.stephie.proyectomultimedios.MainActivity;
import com.example.stephie.proyectomultimedios.Presenter.MainPresenter;

/**
 * Created by neo_free on 05/12/2016.
 */

public class MyAsyncTaskExecutor {

    //private RecyclerView.Adapter mAdapter;

    private static MyAsyncTaskExecutor instance;
    private Context ctx;
    public static MyAsyncTaskExecutor getInstance() {
        if (instance == null) {
            instance = new MyAsyncTaskExecutor();
        }
        return instance;
    }

    //public void executeMyAsynctask(final MainActivity activity, final RecyclerView mRecyclerView, final MainPresenterImpl presenter, final Database dbInstance) {
    public void executeMyAsynctask(final Context ctx, final MainPresenter presenter, final TextView al_disponibles, final TextView al_utilizados, final TextView al_totales, final TextView ce_disponibles, final TextView ce_utilizados, final TextView ce_totales){
        this.ctx = ctx;
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected String doInBackground(Void... params) { // AQUI SE CAE NO SE PORQUE ;·;
                Toast.makeText(ctx,
                        "pre coneccion ", Toast.LENGTH_LONG).show();
                String resultado = new HttpServerConnection().connectToServer("http://www.mocky.io/v2/57f56f722500006e1f134987", 15000);
                Toast.makeText(ctx,
                        "pos coneccion ", Toast.LENGTH_LONG).show();
                return resultado;
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    System.out.println(result);
                    Toast.makeText(ctx,
                            "post asy ", Toast.LENGTH_LONG).show();
                    Datos datos = presenter.getDatos(result);
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

