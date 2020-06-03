package com.example.trabajo_final_app_moviles.entidades;



public class Usuario {

    private String usuario;
    private String contrasenia;
    private String equipo;
    private Integer telefono;
    private byte[] foto;

    public Usuario(String usuario, String contrasenia, String equipo, Integer telefono, byte[] foto) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.equipo = equipo;
        this.telefono = telefono;
        this.foto = foto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}