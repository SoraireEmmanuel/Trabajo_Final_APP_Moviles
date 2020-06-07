package com.example.trabajo_final_app_moviles;

import java.util.Date;

public class Utilidades {

    //constantes campos de la tabla de usuarios
    public  static  final String TABLA_USUARIOS="usuarios";
    public  static  final String CAMPO_USUARIO="usuario";
    public  static  final String CAMPO_CONTRASENIA="contrasenia";
    public  static  final String CAMPO_EQUIPO="equipo";
    public  static  final String CAMPO_TELEFONO="telefono";
    public  static  final String CAMPO_FOTO="foto";


    public  static  final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIOS+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_USUARIO+" STRING, "+CAMPO_CONTRASENIA+" STRING, "+
            CAMPO_EQUIPO+" STRING, "+CAMPO_TELEFONO+" INTEGER, "+CAMPO_FOTO+" IMAGEVIEW)";

    public  static  final String TABLA_TAREAS="tareas";
    public  static  final String CAMPO_ID="id";
    public  static  final String CAMPO_DESCRIPCION="descripcion";
    public  static  final String CAMPO_FECHACREACION="fechaCreacion";
    public  static  final String CAMPO_PRIORIDAD="prioridad";
    public  static  final String CAMPO_TITULO="titulo";
    public  static  final String CAMPO_REALIZADOPOR="realizadoPor";
    public  static  final String CAMPO_REVISADOPOR="revisadoPor";
    public  static  final String CAMPO_CREADOPOR="creadoPor";
    public  static  final String CAMPO_ESTADO="estado";

    public  static  final String CREAR_TABLA_TAREA="CREATE TABLE "+TABLA_TAREAS+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_DESCRIPCION+" STRING, "+
            CAMPO_FECHACREACION+" DATE, "+CAMPO_PRIORIDAD+" STRING, "+CAMPO_TITULO+" STRING, "+CAMPO_REALIZADOPOR+" STRING, "+
            CAMPO_REVISADOPOR+" STRING, "+CAMPO_CREADOPOR+" STRING, "+CAMPO_ESTADO+" STRING)";

    public  static  final String TABLA_EQUIPOS="equipos";

    public  static  final String CREAR_TABLA_EQUIPOS="CREATE TABLE "+TABLA_EQUIPOS+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_DESCRIPCION+" STRING)";

}

