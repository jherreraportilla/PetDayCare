package com.example.petdaycare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petdaycare.Data.PetDbHelper;

public class AltaMascotaActivity extends AppCompatActivity {
    EditText name;
    EditText breed;
    Spinner gender;
    EditText weight;
    Button btnCreate;
    String nombre = "";
    String raza = "";
    String genero = "";
    String peso = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_mascota);
        name = (EditText) findViewById(R.id.editTextNombre);
        breed = (EditText) findViewById(R.id.editTextRaza);
        gender = (Spinner) findViewById(R.id.spinnerGenero);
        weight = (EditText) findViewById(R.id.editTextPeso);
        btnCreate = (Button) findViewById(R.id.buttonCreate);

        gender.setPrompt("Selecciona un género");

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = name.getText().toString();
                raza = breed.getText().toString();
                genero = gender.getSelectedItem().toString();
                peso = weight.getText().toString();


                if(nombre.equalsIgnoreCase("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Tienes que ingresar un nombre",Toast.LENGTH_LONG);
                    toast.show();
                } else if (raza.equalsIgnoreCase("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"Tienes que ingresar una raza",Toast.LENGTH_LONG);
                    toast.show();
                } else if (peso.equalsIgnoreCase("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"Tienes que ingresar una peso",Toast.LENGTH_LONG);
                    toast.show();
                } else if (!raza.matches("^[a-zA-z\\s]+$")) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Tienes que ingresar una raza existente",Toast.LENGTH_LONG);
                    toast.show();
                } else if (Integer.parseInt(peso) > 100 || Integer.parseInt(peso) < 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Tienes que ingresar una peso entre 0 y 100",Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    callInsertNewPet();
                }

            }
        };
        btnCreate.setOnClickListener(onClickListener);


    }

    public void callInsertNewPet() {

        Pet pet = new Pet (nombre,raza, genero, peso);
        PetDbHelper petDbHelper = new PetDbHelper(this);
        double id = petDbHelper.insertPet(pet);
        Toast toast = Toast.makeText(getApplicationContext(),"Nueva entrada en la tabla con el ID: " + id, Toast.LENGTH_SHORT);
        toast.show();

        Intent s = new Intent(this, MainActivity.class);
        startActivity(s);
    }

    /*public static int validaInt(String numero){
        int result = 0;
        try {
            if (numero != null) {
                result = Integer.parseInt(numero);
            }
        }catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return result;
    }*/
}
