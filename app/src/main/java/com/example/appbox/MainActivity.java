package com.example.appbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

// Clase principal
public class MainActivity extends AppCompatActivity
{
    //Asignar var a los objetos de interfaz
    Button btnFragment;

    //Declaracion de variables
    String userc = "", passc = "";


    // Ejecusion de App
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Conexion con sqlite
        ConexionSQLite conn = new ConexionSQLite(this, "box_guias",null,1);

     /*INICIAR NUEVA ACTIVIDAD*/
        //Asignar a var identificar del btn
        Button btn = (Button) findViewById(R.id.sesion);
        EditText username = (EditText)findViewById(R.id.user);
        EditText password = (EditText)findViewById(R.id.pass);
        //Ejecutar metodo Onclick al presionar btn
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // utilizar clase intent para abrir activity home
                if (username.getText().toString().equals(userc) && password.getText().toString().equals(passc)) {
                    Intent intent = new Intent (v.getContext(), Home.class);
                    startActivityForResult(intent, 0);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "Error!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}