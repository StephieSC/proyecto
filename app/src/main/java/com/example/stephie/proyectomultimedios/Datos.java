package com.example.stephie.proyectomultimedios;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Stephie on 04-12-2016.
 */

public class Datos implements Parcelable{
    //Almuerzo
    private String totalAL;
    private String utilizadosAL;
    private String disponiblesAL;
    private ArrayList<Detalle> detalleAL;
    //Cena
    private String totalCE;
    private String utilizadosCE;
    private String disponiblesCE;
    private ArrayList<Detalle> detalleCE;


    public Datos() {
    }

    public Datos(String totalal, String utilizadosal, String disponiblesal, String totalce, String utilizadosce, String disponiblesce, ArrayList<Detalle> detalleal, ArrayList<Detalle> detallece ) {
        //Almuerzo
        this.totalAL = totalal;
        this.utilizadosAL = totalal;
        this.disponiblesAL = disponiblesal;
        this.detalleAL = detalleal;
        //Cena
        this.totalCE = totalce;
        this.utilizadosCE = utilizadosce;
        this.disponiblesCE = disponiblesce;
        this.detalleCE = detallece;

    }

    protected Datos(Parcel in) {
        totalAL = in.readString();
        utilizadosAL = in.readString();
        disponiblesAL = in.readString();
        totalCE = in.readString();
        utilizadosCE = in.readString();
        disponiblesCE = in.readString();
    }

    public static final Creator<Datos> CREATOR = new Creator<Datos>() {
        @Override
        public Datos createFromParcel(Parcel in) {
            return new Datos(in);
        }

        @Override
        public Datos[] newArray(int size) {
            return new Datos[size];
        }
    };

    //Almuerzo
    public String getTotalAL() {
        return totalAL;
    }
    public void setTotalAL(String total) {
        this.totalAL = total;
    }
    public String getUtilizadosAL() {
        return utilizadosAL;
    }
    public void setUtilizadosAL(String utilizados) {
        this.utilizadosAL = utilizados;
    }
    public void setDisponiblesAL(String disponibles) {
        this.disponiblesAL = disponibles;
    }
    public String getDisponiblesAL() {
        return disponiblesAL;
    }
    public void setDetalleAL(ArrayList<Detalle> detalle) {
        this.detalleAL = detalle;
    }
    public ArrayList<Detalle> getDetalleAL() {
        return detalleAL;
    }
    //Cena
    public String getTotalCE() {
        return totalCE;
    }
    public void setTotalCE(String total) {
        this.totalCE = total;
    }
    public String getUtilizadosCE() {
        return utilizadosCE;
    }
    public void setUtilizadosCE(String utilizados) {
        this.utilizadosCE = utilizados;
    }
    public void setDisponiblesCE(String disponibles) {
        this.disponiblesCE = disponibles;
    }
    public String getDisponiblesCE() {
        return disponiblesCE;
    }
    public void setDetalleCE(ArrayList<Detalle> detalle) {
        this.detalleCE = detalle;
    }
    public ArrayList<Detalle> getDetalleCE() {
        return detalleCE;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(totalAL);
        parcel.writeString(utilizadosAL);
        parcel.writeString(disponiblesAL);
        parcel.writeString(totalCE);
        parcel.writeString(utilizadosCE);
        parcel.writeString(disponiblesCE);
        parcel.writeTypedList(detalleAL);
        parcel.writeTypedList(detalleCE);
    }
    public void readFromParcel(Parcel in){
        totalAL=in.readString();
        utilizadosAL=in.readString();
        disponiblesAL=in.readString();
        totalCE=in.readString();
        utilizadosCE=in.readString();
        disponiblesCE=in.readString();

    }
}
