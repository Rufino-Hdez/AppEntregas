package com.example.appbox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbox.utilidades.utilidades;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

// Se agrego: implements View.OnClickListener a la clase principal - para multiples OnClick
public class Home extends AppCompatActivity implements View.OnClickListener
{
    Button btn_scanner, btnregistro, btnConsulta;
    // EditText Scanner
    EditText edtcode;
    //EditText datos
    EditText Ed_name_remit, Ed_address_remit, Ed_name_des, Ed_address_des, Ed_P_Tipo, Ed_P_peso, Ed_P_largo, Ed_P_alto, Ed_P_ancho;

    String codeguia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /* ASIGNAR VARIABLES A CADA BOTON Y PASAMOS SU IDENTIFICADOR */
            //Button
                btn_scanner = findViewById(R.id.btnScaner);
                btnregistro = findViewById(R.id.btnregistro);
                btnConsulta = findViewById(R.id.btnConsulta);

        /* ASIGNAR VARIABLES A CADA EDITTEXT Y PASAMOS SU IDENTIFICADOR */
            //Edit Text
                edtcode = findViewById(R.id.etCodigo);
                Ed_name_remit = findViewById(R.id.Ed_name_remit);
                Ed_address_remit = findViewById(R.id.Ed_address_remit);
                Ed_name_des = findViewById(R.id.Ed_name_des);
                Ed_address_des = findViewById(R.id.Ed_address_des);
                Ed_P_Tipo = findViewById(R.id.Ed_P_Tipo);
                Ed_P_peso = findViewById(R.id.Ed_P_peso);
                Ed_P_largo = findViewById(R.id.Ed_P_largo);
                Ed_P_alto = findViewById(R.id.Ed_P_alto);
                Ed_P_ancho = findViewById(R.id.Ed_P_ancho);

        /* INVOCASION DE METODOS */

            //Ejecutamos el metodo onclick de cada boton
                btn_scanner.setOnClickListener(this);
                btnregistro.setOnClickListener(this);
                btnConsulta.setOnClickListener(this);
    }

/* -- ---------------------------------------------------------------------------------------------- -- */
                        /* M E T O D O S   D E   R E G I S T R O */
/* -- ---------------------------------------------------------------------------------------------- -- */

    // R E G I S T R O   D E  G U I A S

    public void RegistrarGuias()
    {
        ConexionSQLite conexion = new ConexionSQLite(getApplicationContext(),null,null,1);

        String guia = edtcode.getText().toString();
        String N_remitente = Ed_name_remit.getText().toString();
        String D_remitente = Ed_address_remit.getText().toString();
        String N_Destino = Ed_name_des.getText().toString();
        String D_Destino = Ed_address_des.getText().toString();
        String Tp_paquete = Ed_P_Tipo.getText().toString();
        String Peso = Ed_P_peso.getText().toString();
        String Largo = Ed_P_largo.getText().toString();
        String Alto = Ed_P_alto.getText().toString();
        String Ancho = Ed_P_ancho.getText().toString();

        String mensaje = conexion.RegistrarGuias(guia,N_remitente,D_remitente,N_Destino,D_Destino,Tp_paquete,Peso,Largo,Alto,Ancho);
        Toast.makeText(getApplicationContext(), mensaje,Toast.LENGTH_SHORT).show();
    }

/* -- ---------------------------------------------------------------------------------------------- -- */
                        /* M E T O D O S   D E   C O N S U L T A */
/* -- ---------------------------------------------------------------------------------------------- -- */

    // C O N S U L T A   D E  G U I A S

    public void ConsultarGuia(){
        ConexionSQLite conn =new ConexionSQLite(getApplicationContext(),null,null,1);
        String buscar = edtcode.getText().toString();
        String[]rep;
        rep= conn.busrepetidoguia(buscar);

        Ed_name_remit.setText(rep[1]);
        Ed_address_remit.setText(rep[2]);//#pregunta
        Ed_name_des.setText(rep[4]);
        Ed_address_des.setText(rep[5]);
        Ed_P_Tipo.setText(rep[5]);
        Ed_P_peso.setText(rep[6]);
        Ed_P_largo.setText(rep[7]);
        Ed_P_alto.setText(rep[8]);
        Ed_P_ancho.setText(rep[9]);
        Toast.makeText(getApplicationContext(),rep[1],Toast.LENGTH_SHORT).show();
    }


    /*
    Registro con modelos utilities en diferente carpetas
        private void RegistrarGuias()
        {
            //Abrir conexion
            ConexionSQLite conn = new ConexionSQLite(this, "box_guias",null,1);
            // Abrir bd para registrar
            SQLiteDatabase db = conn.getWritableDatabase();
            // clave y valor
            ContentValues values = new ContentValues();
            //Agregar clave y valor asociado a los campos de nuestra BD y el valor agregado por los edittext
                            // Campo BD , Valor txt
            values.put(utilidades.C_guia, edtcode.getText().toString());
            values.put(utilidades.C_NOM_REMITENT, Ed_name_remit.getText().toString());
            values.put(utilidades.C_DIR_REMITENT, Ed_address_remit.getText().toString());
            values.put(utilidades.C_NOM_DESTINA, Ed_name_des.getText().toString());
            values.put(utilidades.C_DIR_DESTINA, Ed_address_des.getText().toString());
            values.put(utilidades.C_TP_PAQ, Ed_P_Tipo.getText().toString());
            values.put(utilidades.C_PESO, Ed_P_peso.getText().toString());
            values.put(utilidades.C_LARGO, Ed_P_largo.getText().toString());
            values.put(utilidades.C_ALTO, Ed_P_alto.getText().toString());
            values.put(utilidades.C_ANCHO, Ed_P_ancho.getText().toString());

            //Insertar a BD
            Long idResultante = db.insert(utilidades.TB_guias, utilidades.C_guia, values);

            Toast.makeText(getApplicationContext(), "idResultante", Toast.LENGTH_SHORT).show();
        }
    */


/* -- ---------------------------------------------------------------------------------------------- -- */
                            /* M E T O D O S   E S C A N E R */
/* -- ---------------------------------------------------------------------------------------------- -- */

    public void Scanner()
    {
        IntentIntegrator intent = new IntentIntegrator(this);
        //IntentIntegrator intent = new IntentIntegrator(getActivity());
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setPrompt("ESCANEAR CODIGO");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelaste el escaneo", Toast.LENGTH_SHORT).show();
            } else {
                edtcode.setText(result.getContents().toString());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

//Se agrego este metodo al implementar : View.OnClickListener en clase principal para multiples ONCLICK
// Si ya no lo necesitamos se debe eliminar este metodo y el implement
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnScaner:
                Scanner();
                break;

            case R.id.btnregistro:
                RegistrarGuias();
                break;

            case R.id.btnConsulta:
                ConsultarGuia();
                break;

            default:
                break;
        }
    }
}
