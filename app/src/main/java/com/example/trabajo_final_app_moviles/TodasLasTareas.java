package com.example.trabajo_final_app_moviles;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TodasLasTareas extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todas_las_tareas);


        /*logica del TAB*/
        Resources res = getResources();
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("En Espera",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("En Ejecucion",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Finalizada",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        tabs.setCurrentTab(0);
    }


    @Override public  boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_logeado_activity, mimenu);
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
            Intent buscar = new Intent(this, Buscar.class);
            startActivity(buscar);
        }

    private void irInformacion(){
        Intent informacion = new Intent(this, Informacion.class);
        startActivity(informacion);
    }

    private void irTareasEliminadas(){
        Intent eliminadas = new Intent(this, TareasEliminadas.class);
        startActivity(eliminadas);
    }

    private void irGrupo(){
        Intent grupo = new Intent(this, Grupos.class);
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
