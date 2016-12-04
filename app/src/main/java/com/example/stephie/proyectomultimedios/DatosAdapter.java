package com.example.stephie.proyectomultimedios;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephie on 04-12-2016.
 */

public class DatosAdapter {

    private static final String URL_FINAL = "http://www.mocky.io/v2/584498ad110000690f0e6b76";
    private List<Datos> items;

    public DatosAdapter() {
        super();
        HttpClient httpClient = new DefaultHttpClient();

        HttpGet del =
                new HttpGet(URL_FINAL);

        del.setHeader("content-type", "application/json");

        try
        {
            HttpResponse resp = httpClient.execute(del);
            String respStr = EntityUtils.toString(resp.getEntity());


            JSONArray respJSON = new JSONArray(respStr);

            items= parseJson(respJSON);
        }
        catch(Exception ex)
        {
            Log.e("ServicioRest","Error!", ex);
        }



    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Referencia del view procesado
        View listItemView;

        //Comprobando si el View no existe
        listItemView = null == convertView ? layoutInflater.inflate(
                R.layout.repositorio,
                parent,
                false) : convertView;


        // Obtener el item actual
        Datos item = items.get(position);

        // Obtener Views
        TextView textoTitulo = (TextView) listItemView.
                findViewById(R.id.nombre_repo);
        TextView textoDescripcion = (TextView) listItemView.
                findViewById(R.id.descripcion_repo);
        TextView textoActualizacion = (TextView) listItemView.
                findViewById(R.id.ultima_actualizacion);

        // Actualizar los Views
        textoTitulo.setText(item.getTotalAL());
        textoDescripcion.setText(item.getUtilizadosAL());
        textoActualizacion.setText(item.getDisponiblesAL());

        return listItemView;
    }

    public List<Datos> parseJson(JSONArray jsonArray){

        List<Datos> datos = new ArrayList<>();
        for(int i=0; i<jsonArray.length(); i++){
            try {
                JSONObject objeto= jsonArray.getJSONObject(i);
                            Datos dato = new Datos(
                            objeto.getString("total_lunch"),
                            objeto.getString("used_lunch"),
                            objeto.getString("available_lunch"),
                            objeto.getString("total_dinner"),
                            objeto.getString("used_dinner"),
                            objeto.getString("available_dinner"),
                            objeto.getString("lunch"),
                            objeto.getString("dinner"));
                    datos.add(dato);
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        return datos;
    }
    public List<Datos>getParse(){return items;}
}
