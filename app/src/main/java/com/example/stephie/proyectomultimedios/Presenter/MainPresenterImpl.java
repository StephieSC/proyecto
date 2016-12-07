package com.example.stephie.proyectomultimedios.Presenter;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stephie.proyectomultimedios.Connections.MyAsyncTaskExecutor;
import com.example.stephie.proyectomultimedios.Datos;
import com.example.stephie.proyectomultimedios.Detalle;
import com.example.stephie.proyectomultimedios.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neo_free on 05/12/2016.
 */

public class MainPresenterImpl implements MainPresenter {
    private Context ctx;
    public static Datos datos;
    public MainPresenterImpl(Context ctx, TextView al_disponibles, TextView al_utilizados, TextView al_totales, TextView ce_disponibles, TextView ce_utilizados, TextView ce_totales, Button al_detalles,Button ce_detalles){
        this.ctx = ctx;
        Datos datos = MyAsyncTaskExecutor.getInstance().executeMyAsynctask(ctx, this, al_disponibles,al_utilizados, al_totales, ce_disponibles, ce_utilizados, ce_totales,al_detalles,ce_detalles);

    }
    @Override
    public Datos getDatos(String result) {
        datos = new Datos();

        try{
            JSONObject jsonObject = new JSONObject(result);
            //Almuerzo
            datos.setTotalAL(jsonObject.optString("total_lunch"));
            datos.setDisponiblesAL(jsonObject.optString("available_lunch"));
            datos.setUtilizadosAL(jsonObject.optString("used_lunch"));
            JSONArray ArrayLunch= jsonObject.getJSONArray("lunch");
            datos.setDetalleAL(parseDetalle(ArrayLunch));
            //Cena
            datos.setTotalCE(jsonObject.optString("total_dinner"));
            datos.setDisponiblesCE(jsonObject.optString("available_dinner"));
            datos.setUtilizadosCE(jsonObject.optString("used_dinner"));
            JSONArray ArrayDinner= jsonObject.getJSONArray("dinner");
            datos.setDetalleCE(parseDetalle(ArrayDinner));

            return datos;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    private ArrayList<Detalle> parseDetalle(JSONArray ArrayComida) throws JSONException {
        List<Detalle> detalles = new ArrayList<>();
        int size = ArrayComida.length();
        for(int i = 0; i < size; i++){
            JSONObject entryObj = ArrayComida.getJSONObject(i);
            Detalle detalle = new Detalle();
            detalle.setDate(entryObj.optString("date"));
            detalle.setHour(entryObj.optString("hour"));
            detalles.add(detalle);
        }
        return (ArrayList<Detalle>) detalles;
    }
    public static Datos obtenerDatos(){return datos;}
}
