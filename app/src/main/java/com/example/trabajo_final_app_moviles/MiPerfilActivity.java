package com.example.trabajo_final_app_moviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MiPerfilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar_perfil);
    }



    @Override public  boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_actualizar_perfil_activity, mimenu);
        return true;
    }

    @Override public  boolean onOptionsItemSelected(MenuItem opcion_menu){
        int id=opcion_menu.getItemId();
        if(id==R.id.buscar) {
            irBuscar();
            return true;
        }
        if(id==R.id.informacion) {
            irInformacion();
            return true;
        }
        if(id==R.id.tareasEliminadas) {
            irTareasEliminadas();
            return true;
        }
        if(id==R.id.grupo) {
            irGrupo();
            return true;
        }
        if(id==R.id.mi_Perfil) {
            irMiPerfil();
            return true;
        }
        if(id==R.id.crearTarea) {
            irCrearTarea();
            return true;
        }
        return super.onOptionsItemSelected(opcion_menu);
    }


    //metodos para moverse a las distintas aplicaciones
    private void irBuscar(){
        Intent buscar = new Intent(this, BuscarActivity.class);
        startActivity(buscar);
    }

    private void irInformacion(){
        Intent informacion = new Intent(this, InformacionActivity.class);
        startActivity(informacion);
    }

    private void irTareasEliminadas(){
        Intent eliminadas = new Intent(this, TareasEliminadasActivity.class);
        startActivity(eliminadas);
    }

    private void irGrupo(){
        Intent grupo = new Intent(this, GruposActivity.class);
        startActivity(grupo);
    }
    private void irMiPerfil(){
        Intent perfil = new Intent(this, MiPerfilActivity.class);
        startActivity(perfil);
    }
    private void irCrearTarea(){
        Intent nueva = new Intent(this, NuevaTareaActivity.class);
        startActivity(nueva);
    }
}
