package com.example.petdaycare;

import androidx.appcompat.app.AppCompatActivity;
import com.example.petdaycare.Data.PetContract.PetEntry;
import com.example.petdaycare.Data.PetDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btn;
    ArrayList<Pet> pets = new ArrayList<Pet>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (FloatingActionButton) findViewById(R.id.btn);
        btn.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAlta();
            }
        });

        //Crea la BBDD en caso de que no esté creada
        PetDbHelper mDbHelper = new PetDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Llamada al método de consulta de datos
        pets = mDbHelper.displayDataBaseInfo();

        //Relleno el ListView
        ListView list = (ListView) findViewById(R.id.itemListPets) ;
        PetAdapter petAdapter = new PetAdapter(this, 0, pets);
        list.setAdapter(petAdapter);


        //Hago clickables los objetos pet del ListView
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(getApplicationContext(),InfoPetActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("pet", pets.get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
        list.setOnItemClickListener(onItemClickListener);


    }
    public void goToAlta(){
        Intent s = new Intent(this, AltaMascotaActivity.class);
        startActivity(s);
    }
}