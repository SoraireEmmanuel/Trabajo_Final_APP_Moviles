package com.example.trabajo_final_app_moviles;

public class Utilidades {

    //constantes campos de la tabla de usuarios
    public static final String TABLA_USUARIOS="usuarios";
    public static final String CAMPO_USUARIO="usuario";
    public static final String CAMPO_CONTRASENIA="contrasenia";
    public static final String CAMPO_EQUIPO="equipo";
    public static final String CAMPO_TELEFONO="telefono";
    public static final String CAMPO_FOTO="foto";


    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIOS+" ("+CAMPO_USUARIO+" STRING, "+CAMPO_CONTRASENIA+" STRING, "+
            CAMPO_EQUIPO+" STRING, "+CAMPO_TELEFONO+" INTEGER, "+CAMPO_FOTO+" IMAGEVIEW)";
}

