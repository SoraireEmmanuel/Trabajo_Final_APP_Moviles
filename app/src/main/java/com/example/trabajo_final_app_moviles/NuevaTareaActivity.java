package com.example.trabajo_final_app_moviles;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NuevaTareaActivity extends AppCompatActivity {
    TextView fecha, titulo, descripcion;
    Spinner prioridad;
    Button crear;
    String inicializacion, estado, id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_tarea);

        //establece la fecha de la tarea con la fecha del sistema
        Date date = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", date.getTime());
        SimpleDateFormat postFormater = new SimpleDateFormat("dd MMMM, yyyy");
        String newDateStr = postFormater.format(date);
        fecha = (TextView) findViewById(R.id.fechatarea);
        fecha.setText(newDateStr);

        //REGISTRO EL SPINNER EN LA VARIABLE spiner PARA MANIPULAR SI VISUALIZACION
        prioridad= (Spinner) findViewById(R.id.spinnerPrioridad);
        String [] opciones = {"Alta", "Media", "Baja"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, opciones);
        prioridad.setPrompt("Prioridad de la Tarea:");
        prioridad.setAdapter(adapter);

        titulo=(EditText)findViewById(R.id.tituloAgregarTarea);
        descripcion=(EditText)findViewById(R.id.descripcionNuevaTarea);
        inicializacion="null";
        estado="Iniciado";
        id="11";
        crear=(Button)findViewById(R.id.crearTarea);

 }
   public void crearTarea(View view) {
            ConexionSQLiteHelper conex = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuario", null, 1);
            SQLiteDatabase db=conex.getWritableDatabase();

            ContentValues values=new ContentValues();
            values.put(Utilidades.CAMPO_DESCRIPCION,descripcion.getText().toString());
            values.put(Utilidades.CAMPO_FECHACREACION,fecha.getText().toString());
            values.put(Utilidades.CAMPO_PRIORIDAD,(String) prioridad.getSelectedItem());
            values.put(Utilidades.CAMPO_TITULO, titulo.getText().toString());
            values.put(Utilidades.CAMPO_REALIZADOPOR, inicializacion );
            values.put(Utilidades.CAMPO_REVISADOPOR, inicializacion);
            values.put(Utilidades.CAMPO_CREADOPOR, inicializacion);
            values.put(Utilidades.CAMPO_ESTADO, estado);

            Long idResultante=db.insert(Utilidades.TABLA_TAREAS,Utilidades.CAMPO_ID,values);
            Toast.makeText(getApplicationContext(),"Id Tarea"+idResultante,Toast.LENGTH_SHORT).show();
   }

    @Override public  boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_logeado_activity, mimenu);
        return true;
    }

}
