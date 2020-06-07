package com.example.trabajo_final_app_moviles;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

public class TodasLasTareasActivity extends AppCompatActivity {
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
        spec.setIndicator("",
                res.getDrawable(R.drawable.tareainiciada));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("",
                res.getDrawable(R.drawable.tarea_en_ejecucion));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("",
                res.getDrawable(R.drawable.tareaenspera));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("",
                res.getDrawable(R.drawable.tarearevisando));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("",
                res.getDrawable(R.drawable.tareafinalizada));
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
