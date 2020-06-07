package com.example.trabajo_final_app_moviles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText cContrasenia, cUsuario, bdContrasenia;
    TextView registra;
    Button ingresa;
    String contrasenia, usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cContrasenia = (EditText) findViewById(R.id.campoContrasenia);
        cUsuario = (EditText) findViewById(R.id.campoUsuario);
        ingresa = (Button) findViewById(R.id.btnIngresar);
        registra = (TextView) findViewById(R.id.registrar);

        registra.setOnClickListener(registrar);
        ingresa.setOnClickListener(ingresar);

    }

    /*nos direcciona al activity Registrar, para crear un registro, al precionar sobre registrar*/
    private View.OnClickListener registrar = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //limpiar();
            Intent regis = new Intent(MainActivity.this, RegistrarActivity.class);
            startActivity(regis);
        }
    };
    private View.OnClickListener ingresar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Boolean validador;
            validador = consultarCampos();
            if (validador.booleanValue()) {
                //Toast.makeText(getApplicationContext(), "Salio verdadero", Toast.LENGTH_SHORT).show();
                perfil();
            } else {
                //Toast.makeText(getApplicationContext(), "Salio falso", Toast.LENGTH_SHORT).show();
            }
        }
    };


    /*inicia sesion */
    private void perfil() {
        Intent iniciar = new Intent(this, TodasLasTareasActivity.class);
        iniciar.putExtra("usuario", cUsuario.getText().toString());
        limpiar();
        startActivity(iniciar);
    }


    /*valida inicio de sesion*/
    public void iniciar(View v) {

    }

    /*trabajar sobre la validacion del formulario*/
    private boolean consultarCampos() {
        usuario = cUsuario.getText().toString();
        contrasenia = cContrasenia.getText().toString();
        if (usuario.equals("")) {
            Toast.makeText(getApplicationContext(), "Tiene que ingresar un Usuario", Toast.LENGTH_SHORT).show();
            cUsuario.requestFocus();
            return false;
        } else {
            if (contrasenia.equals("")) {
                Toast.makeText(getApplicationContext(), "Tiene que ingresar una Contrasenia", Toast.LENGTH_SHORT).show();
                cContrasenia.requestFocus();
                return false;
            } else {
                return consultar();
            }
        }
    }


        private void limpiar() {
            cUsuario.setText("");
            cContrasenia.setText("");
        }


   // consulta la base de datos si el usuario y contraseña coinciden

    private boolean consultar() {
        final ConexionSQLiteHelper conex=new ConexionSQLiteHelper(getApplicationContext(), "bd_usuario", null,1);
        SQLiteDatabase db = conex.getReadableDatabase();

        String[] projection = {Utilidades.CAMPO_CONTRASENIA};
        String selection = Utilidades.CAMPO_USUARIO + " = ?";
        String[] selectionArg = {cUsuario.getText().toString()};

        try{
            Cursor c = db.query(Utilidades.TABLA_USUARIOS, projection,
                    selection , selectionArg, null, null, null);
            c.moveToFirst();

            if (contrasenia.equals(c.getString(0))) {
                Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                return false;
            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El usuario no existe", Toast.LENGTH_SHORT).show();
            limpiar();
            return false;
        }
    }



    //implementacion de salir de la aplicacion
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de RegApp?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}


