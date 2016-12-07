package com.example.stephie.proyectomultimedios.Presenter;

import android.content.Context;

import android.widget.Button;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import android.widget.TextView;

import com.example.stephie.proyectomultimedios.Connections.MyAsyncTaskExecutor;

import com.example.stephie.proyectomultimedios.Models.Datos;
import com.example.stephie.proyectomultimedios.MainActivity;
import com.example.stephie.proyectomultimedios.Models.Detalle;
import com.example.stephie.proyectomultimedios.Models.Students;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by neo_free on 05/12/2016.
 */

public class MainPresenterImpl implements MainPresenter {
    private Context ctx;

    public static Datos datos;
    private Students student;


    private static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final String PROPERTY_EXPIRATION_TIME = "onServerExpirationTimeMs";
    private static final String PROPERTY_USER = "user";

    public static final long EXPIRATION_TIME_MS = 1000 * 3600 * 24 * 7;

    static final String TAG = "GCMDemo";
    String SENDER_ID = "1048878379493";

    private GoogleCloudMessaging gcm;
    private String regid;


    public MainPresenterImpl(Context ctx, TextView al_disponibles, TextView al_utilizados, TextView al_totales, TextView ce_disponibles, TextView ce_utilizados, TextView ce_totales, Button al_detalles,Button ce_detalles){
        this.ctx = ctx;
        Datos datos = MyAsyncTaskExecutor.getInstance().executeMyAsynctask(ctx, this, al_disponibles,al_utilizados, al_totales, ce_disponibles, ce_utilizados, ce_totales,al_detalles,ce_detalles);



        // ic MainPresenterImpl(Context ctx, Context mctx, TextView al_disponibles, TextView al_utilizados, TextView al_totales, TextView ce_disponibles, TextView ce_utilizados, TextView ce_totales){
        //this.ctx = ctx;
        //MyAsyncTaskExecutor.getInstance().executeMyAsynctask(ctx, this);
        //MyAsyncTaskExecutor.getInstance().executeMyAsynctask(ctx, this, al_disponibles,al_utilizados, al_totales, ce_disponibles, ce_utilizados, ce_totales);
        //registroGCM();


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


    public Students getStudent(String result)
    {
        Students student = new Students();
        try{
            JSONObject jsonObject = new JSONObject(result);
            student.setName(jsonObject.optString("name"));
            student.setPhoto(jsonObject.optString("image"));
            student.setRol(jsonObject.optString("rol"));
            return student;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getRegistrationId(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        String registrationId = prefs.getString(PROPERTY_REG_ID,"");
        if (registrationId.length() == 0){
            Log.d(TAG, "Registro GCM no encontrado.");
            return "";
        }

        String registeredUser = prefs.getString(PROPERTY_USER, "user");
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        long expirationTime =
                prefs.getLong(PROPERTY_EXPIRATION_TIME, -1);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String expirationDate = sdf.format(new Date(expirationTime));

        Log.d(TAG, "Registro GCM encontrado (usuario=" + registeredUser +
                ", version=" + registeredVersion +
                ", expira=" + expirationDate + ")");

        int currentVersion = getAppVersion(ctx);

        if (registeredVersion != currentVersion)
        {
            Log.d(TAG, "Nueva versión de la aplicación.");
            return "";
        }
        else if (System.currentTimeMillis() > expirationTime)
        {
            Log.d(TAG, "Registro GCM expirado.");
            return "";
        }
        /*else if (!txtUsuario.getText().toString().equals(registeredUser))
        {
            Log.d(TAG, "Nuevo nombre de usuario.");
            return "";
        }*/

        return registrationId;
    }

    private static int getAppVersion(Context context)
    {
        try
        {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);

            return packageInfo.versionCode;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            throw new RuntimeException("Error al obtener versión: " + e);
        }
    }

    private class TareaRegistroGCM extends AsyncTask<Students,Integer,String>
    {
        @Override
        protected String doInBackground(Students... params)
        {
            String msg = "";
            Students student_in = params[0];
            try
            {
                if (gcm == null)
                {
                    gcm = GoogleCloudMessaging.getInstance(ctx);
                }

                //Nos registramos en los servidores de GCM
                regid = gcm.register(SENDER_ID);
                student_in.setToken(regid);
                Log.d(TAG, "Registrado en GCM: registration_id=" + regid);

                //Nos registramos en nuestro servidor
                boolean registrado = registroServidor(student_in.getName(), student_in.getRol(),student_in.getPhoto(), student_in.getToken());

                //Guardamos los datos del registro
                if(registrado)
                {
                    setRegistrationId(ctx, regid, student_in);
                }
            }
            catch (IOException ex)
            {
                Log.d(TAG, "Error registro en GCM:" + ex.getMessage());
            }

            return msg;
        }
    }

    private void setRegistrationId(Context context, String regId, Students student_in)
    {
        SharedPreferences prefs = ctx.getSharedPreferences(
                MainActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);

        int appVersion = getAppVersion(context);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_USER, student_in.getName());
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.putLong(PROPERTY_EXPIRATION_TIME,
                System.currentTimeMillis() + EXPIRATION_TIME_MS);

        editor.commit();
    }
    private boolean registroServidor(String nombre, String rol, String photo, String regId)
    {
        boolean reg = false;
        try{
            URL url = new URL("http://192.168.1.33:3000/api/students");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(15000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            Log.d(TAG, "creando registro en el servidor ");
            String json = "{ \"name\": "+nombre+", \"photo\": "+photo+",\"rol\": "+rol+"\", \"token\": "+regId+"}";
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            //os.flush();
            os.close();
            Log.d(TAG, "creando registro en el servidor ");
            reg = true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reg;
    }
    public void registroGCM(Students student_in)
    {
        Log.d(TAG, "registroGCM: En el GCM");
        gcm = GoogleCloudMessaging.getInstance(ctx);
        regid = getRegistrationId(ctx);
        if(regid.equals("")){

            TareaRegistroGCM tarea = new TareaRegistroGCM();
            tarea.execute(student_in);
        }
    }
}
