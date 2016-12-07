package com.example.stephie.proyectomultimedios.Presenter;

import android.content.Context;

import com.example.stephie.proyectomultimedios.Models.Datos;
import com.example.stephie.proyectomultimedios.Models.Students;

/**
 * Created by neo_free on 05/12/2016.
 */

public interface MainPresenter {

    public Datos getDatos(String result);
    public Students getStudent(String result);
    public String getRegistrationId(Context ctx);
    public void registroGCM(Students student);


}
