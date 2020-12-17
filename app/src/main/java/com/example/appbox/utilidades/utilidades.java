package com.example.appbox.utilidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.appbox.ConexionSQLite;
import com.example.appbox.entidades.guias;

public class utilidades {
   /*
   //Declarar constantes con los campos que tendra nuestra DB
   //Nombre de tabla
   public static  final String TB_guias = "guias";
   //Campos
   public static  final String C_guia = "guia";
   public static  final String C_NOM_REMITENT = "nombre_remitente";
   public static  final String C_DIR_REMITENT = "direccion_remitente";
   public static  final String C_NOM_DESTINA = "nombre_destinatario";
   public static  final String C_DIR_DESTINA = "direccion_destinatario";
   public static  final String C_TP_PAQ = "tipo_paquete";
   public static  final String C_PESO = "peso_paquete";
   public static  final String C_LARGO = "tipo_paquete";
   public static  final String C_ALTO = "tipo_paquete";
   public static  final String C_ANCHO = "tipo_paquete";

   public static final String CREAR_TBL_GUIAS = "CREATE TABLE guias " +
            "(id	INTEGER NOT NULL UNIQUE," +
            "guia VARCHAR(30) NOT NULL, " +
            "nombre_remitente	VARCHAR(30) NOT NULL, " +
            "direccion_remitente	VARCHAR(30) NOT NULL,"+
            "nombre_destinatario	VARCHAR(30) NOT NULL, " +
            "direccion_destinatario	VARCHAR(30), " +
            "tipo_paquete	VARCHAR(30) NOT NULL, " +
            "peso_paquete	VARCHAR(10) NOT NULL, " +
            "largo	VARCHAR(10) NOT NULL, " +
            "alto	VARCHAR(10) NOT NULL," +
            "ancho	VARCHAR(10)"
            ;
   */
    /*
    //Definir metodo para conexion con la BD
    public SQLiteDatabase getConn(Context context ){
        //Accedemos a la clase creada ConexionSQLite
        //Accedemos al contructor context que tambien declaramos en esta clase
        ConexionSQLite conn = new ConexionSQLite(context, "box_guias",null,1);
        //Crear obj a retornar como objeto de escritura para permitir el insert
        SQLiteDatabase db = conn.getWritableDatabase();
        return db;
    }

    //Metodo insert regresa un entero
    //Recibe parametro tipo context y pasamos todos los campos creados en la clase guiasDTO
    int insertaGuia(Context context, guias dto)
    {
        //retornar
        int res = 0;
        String sql = "INSERT INTO guias (id, guia, nombre_remitente, direccion_remitente, nombre_destinatario, direccion_destinatario, tipo_paquete, peso_paquete, largo, alto, ancho)" +
                "VALUES(' " +dto.getId()+" ' , ' "+dto.getGuia()+" ', ' " +dto.getNombre_remitente()+" ' , ' " +dto.getDireccion_remitente()+" ' , ' " +dto.getNombre_destinatario()+" ' , ' " +dto.getDireccion_destinatario()+" ' , ' " +dto.getTipo_paquete()+" ', ' " +dto.getPeso_paquete()+" ', ' " +dto.getLargo()+" ', ' " +dto.getAlto()+" ', ' " +dto.getAncho()+" ')";
        SQLiteDatabase db = this.getConn(context);

        try {
            db.execSQL(sql);
            res = 1;
        }catch (Exception e){

        }
        return res;

    }
    */
}
