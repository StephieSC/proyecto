package com.example.stephie.proyectomultimedios;

import java.util.ArrayList;

/**
 * Created by Stephie on 04-12-2016.
 */

public class Datos {
    //Almuerzo
    private String totalAL;
    private String utilizadosAL;
    private String disponiblesAL;
    private ArrayList detalleAL;
    //Cena
    private String totalCE;
    private String utilizadosCE;
    private String disponiblesCE;
    private ArrayList detalleCE;


    public Datos() {
    }

    public Datos(String totalal, String utilizadosal, String disponiblesal,String totalce, String utilizadosce, String disponiblesce, ArrayList detalleal, ArrayList detallece ) {
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
    public void setDetalleAL(ArrayList detalle) {
        this.detalleAL = detalle;
    }
    public ArrayList getDetalleAL() {
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
    public void setDetalleCE(ArrayList detalle) {
        this.detalleCE = detalle;
    }
    public ArrayList getDetalleCE() {
        return detalleCE;
    }
}
