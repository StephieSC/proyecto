package com.example.stephie.proyectomultimedios;

/**
 * Created by Stephie on 06-12-2016.
 */

public class Detalle {
    private String date;
    private String hour;

    public Detalle(){}
    public Detalle(String dia, String hora){
        this.date=dia;
        this.hour=hora;
    }
    public String getDate(){return this.date;}
    public String getHour(){return this.hour;}
    public void setDate(String dia){this.date=dia;}
    public void setHour(String hora){this.hour=hora;}

}
