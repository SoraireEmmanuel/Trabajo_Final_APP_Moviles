package com.example.trabajo_final_app_moviles.entidades;

import java.util.Date;

public class Tareas {


    private Integer id;
    private String descripcion;
    private Date fechaCreacion;
    private String prioridad;
    private String titulo;
    private String realizadoPor;
    private String revisadoPor;
    private String creadoPor;
    private String estado;

    public Tareas(Integer id, String descripcion, Date fechaCreacion, String prioridad, String titulo, String realizadoPor,
                  String revisadoPor, String creadoPor, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.prioridad = prioridad;
        this.titulo = titulo;
        this.realizadoPor=realizadoPor;
        this.revisadoPor=revisadoPor;
        this.creadoPor=creadoPor;
        this.estado=estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRealizadoPor() {
        return realizadoPor;
    }

    public void setRealizadoPor(String realizadoPor) {
        this.realizadoPor = realizadoPor;
    }

    public String getRevisadoPor() {
        return revisadoPor;
    }

    public void setRevisadoPor(String revisadoPor) {
        this.revisadoPor = revisadoPor;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
