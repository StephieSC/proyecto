package com.example.stephie.proyectomultimedios;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stephie on 06-12-2016.
 */

public class Detalle implements Parcelable {
    private String date;
    private String hour;

    public Detalle(){}
    public Detalle(String dia, String hora){
        this.date=dia;
        this.hour=hora;
    }

    protected Detalle(Parcel in) {
        date = in.readString();
        hour = in.readString();
    }

    public static final Creator<Detalle> CREATOR = new Creator<Detalle>() {
        @Override
        public Detalle createFromParcel(Parcel in) {
            return new Detalle(in);
        }

        @Override
        public Detalle[] newArray(int size) {
            return new Detalle[size];
        }
    };

    public String getDate(){return this.date;}
    public String getHour(){return this.hour;}
    public void setDate(String dia){this.date=dia;}
    public void setHour(String hora){this.hour=hora;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(hour);
    }
    public void readFromParcel(Parcel in){
        date=in.readString();
        hour= in.readString();
    }
}
