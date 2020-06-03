package com.example.trabajo_final_app_moviles;

import android.app.AlertDialog;
import android.app.AppComponentFactory;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Registrar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    ImageView imagen;
    Spinner spiner;
    String path;
    Button registro;
    EditText campoUsuario;
    EditText campoNombre;
    EditText campoApellido;
    EditText campoContrasenia;
    EditText campoTelefono;
    String spinerText;
    Bitmap imgByte;
    Boolean bandera=false;

    //componentes de la conccion a la BBDD
    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

//variables para la carga de foto
    private final String CARPETA_RAIZ="misImagenes/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"misFotos";

    final int COD_SELECCIONADA=10;
    final int COD_FOTO=20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);

        //REGISTRO LA IMAGEN DEL FORMULARIO EN LA CONSTANTE imagen
        imagen=(ImageView)findViewById(R.id.imagenId);

        //REGISTRO EL SPINNER EN LA VARIABLE spiner PARA MANIPULAR SI VISUALIZACION
        spiner=(Spinner)findViewById(R.id.spinner);
        String [] opciones = {"Equipo1","Equipo2","Equipo3","Equipo4", "Equipo5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, opciones);
        spiner.setPrompt("Seleccione su equipo");
        spiner.setAdapter(adapter);

        //REGISTRO EL RESTO DE LOS CAMPOS PARA SU CARGA EN LA BASE DE DATOS
        campoUsuario=(EditText)findViewById(R.id.nombre);
        campoContrasenia =(EditText)findViewById(R.id.contrasenia);
        campoTelefono = (EditText)findViewById(R.id.telefono);

        //CODIGO BASE DE DATOS
        //request= Volley.newRequestQueue(this);
        //registro = (Button) findViewById(R.id.btnRegistroForm);
    }
/*abandone el uso de una base de datos externa, este codigo queda por si se implementa en un futuro
    private void cargarWebService() {
        // transforma el bit map en arreglo de byte
       /* ByteArrayOutputStream bos = new ByteArrayOutputStream();
        imgByte.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] img = bos.toByteArray();

        progreso=new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

        String url = "http://192.168.0.198/appmovilestrabajofinal/wsJSONRegistro.php?usuario="+campoUsuario.getText().toString()+
                "&contrasenia="+campoContrasenia.getText().toString()+"&equipo="+(String) spiner.getSelectedItem()+"&telefono="+campoTelefono.getText().toString();
        //para admitir espacio en los campos
        //url=url.replace(" ", "%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }*/

    public void registar(View view){
        if(bandera) {registrarUsuario();}
        else{
            Toast.makeText(getApplicationContext(),"Se tiene que cargar una imagen para registrarse",Toast.LENGTH_SHORT).show();}
    }

    private void registrarUsuario() {
        // transforma el bit map en arreglo de byte
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        imgByte.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] img = bos.toByteArray();




        ConexionSQLiteHelper conex = new ConexionSQLiteHelper(this, "bd_usuario", null, 1);
        SQLiteDatabase db=conex.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_USUARIO,campoUsuario.getText().toString());
        values.put(Utilidades.CAMPO_CONTRASENIA,campoContrasenia.getText().toString());
        values.put(Utilidades.CAMPO_EQUIPO,(String) spiner.getSelectedItem());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());
        values.put(Utilidades.CAMPO_FOTO, img);

        Long idResultante=db.insert(Utilidades.TABLA_USUARIOS,Utilidades.CAMPO_USUARIO,values);

        Toast.makeText(getApplicationContext(),"DNI registro:"+idResultante,Toast.LENGTH_SHORT).show();
    }


    //metodos para la carga de foto para versiones de android 5.1 Lollipop o menores
    public void cargarImagen(View view) {
        cargar();
    }

    private void cargar(){
        final CharSequence[] opciones={"Tomar Foto","Cargar Imagen","Cancelar"};
        final AlertDialog.Builder alertOpcion=new AlertDialog.Builder(Registrar.this);
        alertOpcion.setTitle("Seleccione una Opcion");
        alertOpcion.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(opciones[i].equals("Tomar Foto")){
                    tomarFoto();
                }
                else{
                    if(opciones[i].equals("Cargar Imagen")){
                        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicacion"),COD_SELECCIONADA);
                    }
                    else{
                        dialog.dismiss();
                    }
                }
            }
        });
        alertOpcion.show();
    }

    private void tomarFoto() {
        String nombreImagen="";
        File fileImagen=new File(Environment.getExternalStorageDirectory(),RUTA_IMAGEN);
        boolean isCreada= fileImagen.exists();
        if(isCreada==false){
            isCreada=fileImagen.mkdirs();
        }
        if(isCreada==true) {
            nombreImagen = (System.currentTimeMillis() / 1000) + ".jpg";
        }
        path=Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+File.separator+nombreImagen;
        File imag=new File(path);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imag));
        startActivityForResult(intent,COD_FOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case COD_SELECCIONADA:
                    Uri miPath=data.getData();
                    imagen.setImageURI(miPath);
                    try {
                        imgByte = MediaStore.Images.Media.getBitmap(this.getContentResolver(), miPath);
                        bandera=true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case COD_FOTO:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("Ruta de almacenamiento","Path: "+path);
                        }
                    });
                    Bitmap bitmap= BitmapFactory.decodeFile(path);
                    imagen.setImageBitmap(bitmap);
                    imgByte= bitmap;
                    bandera=true;
                    break;
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getBaseContext(), "Algo salio mal",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast.makeText(getBaseContext(), "Se realizo correctamente la insercion",Toast.LENGTH_SHORT).show();

    }
}
