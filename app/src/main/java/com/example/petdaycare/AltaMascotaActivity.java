package com.example.petdaycare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

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
                if(comprobarDatos()){
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

    public boolean comprobarDatos(){
        boolean sonCorrectos = false;
        nombre = name.getText().toString();
        raza = breed.getText().toString();
        genero = gender.getSelectedItem().toString();
        peso = weight.getText().toString();

        if(nombre.equalsIgnoreCase("") && raza.equalsIgnoreCase("") && peso.equalsIgnoreCase("")){
            name.setError("Ingresa un nombre");
            name.requestFocus();
            breed.setError("Ingresa una raza");
            breed.requestFocus();
            weight.setError("Ingresa un peso");
            weight.requestFocus();
            Toast toast = Toast.makeText(getApplicationContext(),"No pueden haber campos vacíos" , Toast.LENGTH_SHORT);
            toast.show();
        } else if(nombre.equalsIgnoreCase("")) {
            name.setError("Ingresa un nombre");
            name.requestFocus();
        } else if (raza.equalsIgnoreCase("")){
            breed.setError("Ingresa una raza");
            breed.requestFocus();
        } else if (peso.equalsIgnoreCase("")){
            weight.setError("Ingresa un peso");
            weight.requestFocus();
        } else if (!raza.matches("^[a-zA-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙñÑ\\s]+$")) {
            breed.setError("Ingresa una raza válida");
            breed.requestFocus();
        } else if (Integer.parseInt(peso) > 100 || Integer.parseInt(peso) < 0) {
            weight.setError("Ingresa un peso coherente");
            weight.requestFocus();
        } else {
            sonCorrectos = true;
        }
        return sonCorrectos;
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                Log.i("Entrando", "Entré");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
