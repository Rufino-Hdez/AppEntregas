package com.example.appbox;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.appbox.utilidades.utilidades;

public class ConexionSQLite extends SQLiteOpenHelper {

    // Sentencia SQL crear Tabla guias
            String CREAR_TBL_GUIAS = "CREATE TABLE guias " +
                    "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "guia VARCHAR(30), " +
                        "nombre_remitente	VARCHAR(30), " +
                        "direccion_remitente	VARCHAR(30),"+
                        "nombre_destinatario	VARCHAR(30), " +
                        "direccion_destinatario	VARCHAR(30), " +
                        "tipo_paquete	VARCHAR(30), " +
                        "peso_paquete	VARCHAR(10), " +
                        "largo	VARCHAR(10), " +
                        "alto	VARCHAR(10)," +
                        "ancho	VARCHAR(10)" +
                    ")";

    //************************************************************//
// Constructor //
    // Asignar nombre de DB
    public ConexionSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "BaseBox", factory, 1);
    }

// OnCreate - CREACION DE TABLAS //
    @Override /*Pasamos bd_box: nombre de nuestra db*/
    public void onCreate(SQLiteDatabase BaseBox) {
        /*Ejecuta sentencia SQL*/
            BaseBox.execSQL(CREAR_TBL_GUIAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd_box, int oldVersion, int newVersion) {
        //Verifica si hay una version antigua de la db
        /*REFRESH SCRIPTS*/
        //bd_box.execSQL("DROP TABLE IF EXISTS CREAR_TBL_GUIAS");
    }

// METODO - Insercion de datos de guias -
        public String RegistrarGuias(
                      String guia,String NomRemitente,
                      String DirRemitente,String NomDestino,
                      String DirDestino, String TpPaquete,
                      String Peso, String Largo, String Alto, String Ancho)
        {
            String msj; // String mostrar mensaje
            SQLiteDatabase database= this.getWritableDatabase(); // Conexion bd para permitir registrar

            ContentValues contenedor= new ContentValues();
            // Put("Campo BD", Variable que almacena el valor de EditText)
            contenedor.put("guia",guia);
            contenedor.put("nombre_remitente",NomRemitente);
            contenedor.put("direccion_remitente",DirRemitente);
            contenedor.put("nombre_destinatario",NomDestino);
            contenedor.put("direccion_destinatario",DirDestino);
            contenedor.put("tipo_paquete",TpPaquete);
            contenedor.put("peso_paquete",Peso);
            contenedor.put("largo",Largo);
            contenedor.put("alto",Alto);
            contenedor.put("ancho",Ancho);
        // Captura de error
            try {
                // Exito
                database.insertOrThrow("guias",null,contenedor);
                msj="Guia Alamacenado correctamente";
            }catch (SQLException e){
                //Error
                msj="Ocurrio un error";
            }
            return msj;
        }



// ** CONSULTA DE GUIAS ** //
//Consulta si no hay el mismo registro
    public String [] busrepetidoguia (String buscar)
        {
            String[] dat= new String[12];
            SQLiteDatabase database = this.getWritableDatabase();
            String q="SELECT * FROM guias WHERE guia='"+buscar+"' ";
            Cursor regis= database.rawQuery(q,null);
            if (regis.moveToFirst()){
                for (int i =0; i<11; i++){ dat[i]=regis.getString(i); }
                dat[11]="busRepetido_Existe";
            }else{ dat[11]="busRepetido_No Existe"; }return dat;
        }
    /*/CONSULTA DE INFORMACION
    public String [] ConsultarGuia( String buscar_guia){
        {
            String[] datos= new String[11];

            SQLiteDatabase db = this.getReadableDatabase();

            String query="SELECT * FROM guias WHERE guia='"+buscar_guia+"'";
            Cursor regis= db.rawQuery(query,null);
            //Inconcluso -
            if (regis.moveToFirst()){
                for (int i =0; i<10; i++){
                    datos[i]=regis.getString(i);
                }
                datos[11]="Encontrado";
            }else{
                datos[11]="No se Encontro";
            }
            return datos;
        }
    } */

}
