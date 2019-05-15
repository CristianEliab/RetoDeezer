package com.appmoviles.retomoviles.model;

import java.io.Serializable;

public class PlayList implements Serializable {

    private String nombrePlayList;
    private String nombreCreador;
    private String numeroCansiones;
    private String descripcion;
    private String numeroFans;
    private String icon_play_list;
    private String trackList;
    private String id_playList;

    public PlayList(String nombrePlayList, String nombreCreador, String numeroCansiones, String descripcion, String numeroFans) {
        this.nombrePlayList = nombrePlayList;
        this.nombreCreador = nombreCreador;
        this.numeroCansiones = numeroCansiones;
        this.descripcion = descripcion;
        this.numeroFans = numeroFans;
    }

    public PlayList() {
    }

    public String getNombrePlayList() {
        return nombrePlayList;
    }

    public void setNombrePlayList(String nombrePlayList) {
        this.nombrePlayList = nombrePlayList;
    }

    public String getNombreCreador() {
        return nombreCreador;
    }

    public void setNombreCreador(String nombreCreador) {
        this.nombreCreador = nombreCreador;
    }

    public String getNumeroCansiones() {
        return numeroCansiones;
    }

    public void setNumeroCansiones(String numeroCansiones) {
        this.numeroCansiones = numeroCansiones;
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

    public String getIcon_play_list() {
        return icon_play_list;
    }

    public void setIcon_play_list(String icon_play_list) {
        this.icon_play_list = icon_play_list;
    }

    public String getTrackList() {
        return trackList;
    }

    public void setTrackList(String trackList) {
        this.trackList = trackList;
    }

    public String getId_playList() {
        return id_playList;
    }

    public void setId_playList(String id_playList) {
        this.id_playList = id_playList;
    }
}
