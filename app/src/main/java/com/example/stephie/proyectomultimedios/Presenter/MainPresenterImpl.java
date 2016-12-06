package com.example.stephie.proyectomultimedios.Presenter;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stephie.proyectomultimedios.Connections.MyAsyncTaskExecutor;
import com.example.stephie.proyectomultimedios.Datos;
import com.example.stephie.proyectomultimedios.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by neo_free on 05/12/2016.
 */

public class MainPresenterImpl implements MainPresenter {
    private Context ctx;
    private Datos datos;
    public MainPresenterImpl(Context ctx, TextView al_disponibles, TextView al_utilizados, TextView al_totales, TextView ce_disponibles, TextView ce_utilizados, TextView ce_totales){
        this.ctx = ctx;
        Datos datos = MyAsyncTaskExecutor.getInstance().executeMyAsynctask(ctx, this, al_disponibles,al_utilizados, al_totales, ce_disponibles, ce_utilizados, ce_totales);

    }
    @Override
    public Datos getDatos(String result) {
        Datos datos = new Datos();
        try{
            JSONObject jsonObject = new JSONObject(result);
            datos.setTotalAL(jsonObject.optString("total_lunch"));
            datos.setDisponiblesAL(jsonObject.optString("available_lunch"));
            datos.setUtilizadosAL(jsonObject.optString("used_lunch"));
            datos.setTotalCE(jsonObject.optString("total_dinner"));
            datos.setDisponiblesCE(jsonObject.optString("available_dinner"));
            datos.setUtilizadosCE(jsonObject.optString("used_dinner"));
            //Agregar la lista de detalles proximamente
            return datos;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
