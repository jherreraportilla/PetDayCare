package com.example.petdaycare;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class InfoPetActivity extends AppCompatActivity {
    EditText name;
    EditText breed;
    EditText gender;
    EditText weight;
    Button btnSave;
    String nombre = "";
    String raza = "";
    String genero = "";
    String peso = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_mascota);

        name = (EditText) findViewById(R.id.editTextNombre);
        breed = (EditText) findViewById(R.id.editTextRaza);
        gender = (EditText) findViewById(R.id.spinnerGenero);
        weight = (EditText) findViewById(R.id.editTextPeso);
        btnSave = (Button) findViewById(R.id.buttonSave);



        //Recibimos los datos del MainActivity
        Bundle receivedObject = getIntent().getExtras();
        Pet receivedPet = (Pet)receivedObject.getSerializable("pet");
        String nombre = receivedPet.getName().toString();
        String raza = receivedPet.getBreed().toString();
        String genero = receivedPet.getGender().toString();
        String peso = receivedPet.getWeight().toString();

        name.setText(nombre);
        breed.setText(raza);
        gender.setText(genero);
        weight.setText(peso);
    }
    
}
