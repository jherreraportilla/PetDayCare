package com.example.petdaycare.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.petdaycare.Data.PetContract.PetEntry;
import com.example.petdaycare.Pet;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PetDbHelper extends SQLiteOpenHelper {

    /*Nombre de la Base de datos*/
    private static final String DATABASE_NAME = "pets.db";
    /*Versión actual de la BBDD, si modificamos el esquema tenemos que modificar el número*/
    private static final int DATABASE_VERSION = 1;

    public PetDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public long insertPet (Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PetEntry.COLUMN_PET_NAME, pet.getName());
        contentValues.put(PetEntry.COLUMN_PET_BREED, pet.getBreed());
        contentValues.put(PetEntry.COLUMN_PET_WEIGHT, pet.getWeight());
        contentValues.put(PetEntry.COLUMN_PET_GENDER, pet.getGender());


        long newRowId = db.insert(PetEntry.TABLE_NAME, null, contentValues);

        return newRowId;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + PetEntry.TABLE_NAME + "("
                + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
                + PetEntry.COLUMN_PET_BREED + " TEXT NOT NULL, "
                + PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0,"
                + PetEntry.COLUMN_PET_GENDER + " TEXT NOT NULL);";


        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Pet> displayDataBaseInfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Pet> pets = new ArrayList<Pet>();
        //La projection nos va a indicar las columnas de la tabla que nos interesa consultar
        String [] projection = {
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED,
                PetEntry.COLUMN_PET_WEIGHT,
                PetEntry.COLUMN_PET_GENDER
        };

        Cursor cursor = db.query(
                PetEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        //Obtenemos los indices de nuestras columnas.
        int nameColumn = cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME);
        int breedColumn = cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED);
        int weightColumn = cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT);
        int genderColumn = cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER);


        //Con cada uno de los indices ya podemos recorrer las filas.
        while (cursor.moveToNext()) {
            String currentName = cursor.getString(nameColumn);
            String currentBreed = cursor.getString(breedColumn);
            String currentWeight = cursor.getString(weightColumn);
            String currentGender = cursor.getString(genderColumn);


            if (currentName.isEmpty() || currentBreed.isEmpty() || currentGender.isEmpty() || currentWeight.isEmpty()) {
                pets.add(null);
            } else {
                Pet currentPet = new Pet(currentName, currentBreed, currentGender, currentWeight);
                pets.add(currentPet);
            }


        }
        return pets;
    }
}
