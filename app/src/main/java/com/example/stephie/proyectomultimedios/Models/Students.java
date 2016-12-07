package com.example.stephie.proyectomultimedios.Models;

/**
 * Created by neo_free on 06/12/2016.
 */

public class Students {

    private String name;
    private String rol;
    private String token;
    private String photo;

    public Students(){

    }
    public Students(String name, String rol, String token, String photo){
        this.name = name;
        this.rol = rol;
        this.token = token;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getRol() {
        return rol;
    }

    public String getToken() {
        return token;
    }

    public String getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
