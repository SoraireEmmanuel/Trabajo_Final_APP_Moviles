package com.example.trabajo_final_app_moviles;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo_final_app_moviles.Adaptadores.AdaptadorTarea;
import com.example.trabajo_final_app_moviles.entidades.Tareas;
import com.example.trabajo_final_app_moviles.entidades.Usuario;

import java.util.ArrayList;

public class TodasLasTareasActivity extends AppCompatActivity implements AdaptadorTarea.ListClick{
    RecyclerView recicle;
    ArrayList<Tareas> listaTareas;
    TextView identificador, titulo, prioridad, fecha;

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

        //configuracion del RecyclerViewIniciado
        recicle=findViewById(R.id.recyclerViewIniciada);
        recicle.setLayoutManager(new LinearLayoutManager(this));
        cargarTarea("Iniciado");
        AdaptadorTarea adapter=new AdaptadorTarea(listaTareas, this);
        recicle.setAdapter(adapter);
    }

//creacion del menu
    @Override public  boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_logeado_activity, mimenu);
        return true;
}
//opciones de los items del menu
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

//consulta en la base de datos
/*consulta el usuario a la base de datos y lo carga*/
private void cargar(String estado){
    listaTareas=new ArrayList<Tareas>();
    listaTareas.add(new Tareas(33,"compar pan","alta","11/10/2020"));
}


    private void cargarTarea(String estado) {
    listaTareas=new ArrayList<Tareas>();
    final ConexionSQLiteHelper conex=new ConexionSQLiteHelper(getApplicationContext(), "bd_usuario", null,1);
    SQLiteDatabase db = conex.getReadableDatabase();
    String selection = Utilidades.CAMPO_ESTADO + " = ?";
    String[] selectionArg = {"Iniciada"};
    String [] proyeccion = {Utilidades.CAMPO_ID,Utilidades.CAMPO_TITULO,Utilidades.CAMPO_PRIORIDAD,Utilidades.CAMPO_FECHACREACION};
    try{
        Cursor c = db.query(Utilidades.TABLA_TAREAS, null,
                null , null, null, null, null);
        c.moveToFirst();
        listaTareas.add(new Tareas( c.getInt(0),c.getString(1), c.getString(2),c.getString(3)) );
      while(c.moveToNext()) {
            listaTareas.add(new Tareas(c.getInt(0), c.getString(1), c.getString(2),c.getString(3)));
        }




        //        Toast.makeText(getApplicationContext(),"El usuario  existe", Toast.LENGTH_SHORT).show();
    }catch (Exception e){
        //       Toast.makeText(getApplicationContext(),"El usuario no existe", Toast.LENGTH_SHORT).show();
    }
}


    //metodos para trabajar con los recicleview
    //metodo para ir a la descripcion de la tarea cuando se hace click en una tarea del listado
    @Override
    public void onListClick(Integer id, String titulo, String prioridad, String fechaCreacion) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
