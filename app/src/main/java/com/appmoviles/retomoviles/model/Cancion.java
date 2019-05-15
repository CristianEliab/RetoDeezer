package com.appmoviles.retomoviles.model;

import java.io.Serializable;

public class Cancion implements Serializable {

    private String nombreCancion;
    private String nombreArtista;
    private String anioLanzamiento;
    private String descripcion;
    private String numeroFans;
    private String icon;
    private String cancion;
    private String id_cancion;
    private String albun_cancion;

    public Cancion() {
    }

    public Cancion(String nombreCancion, String nombreArtista, String anioLanzamiento, String descripcion, String numeroFans) {
        this.nombreCancion = nombreCancion;
        this.nombreArtista = nombreArtista;
        this.anioLanzamiento = anioLanzamiento;
        this.descripcion = descripcion;
        this.numeroFans = numeroFans;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(String anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroFans() {
        return numeroFans;
    }

    public void setNumeroFans(String numeroFans) {
        this.numeroFans = numeroFans;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public String getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(String id_cancion) {
        this.id_cancion = id_cancion;
    }

    public String getAlbun_cancion() {
        return albun_cancion;
    }

    public void setAlbun_cancion(String albun_cancion) {
        this.albun_cancion = albun_cancion;
    }
}
