package com.example.petdaycare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PetAdapter extends ArrayAdapter<Pet> {
    public PetAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Pet> pets) {
        super(context, resource, pets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View list_item = convertView;
        //Verificar si la vista está vacía, si es así la inflamos
        if(list_item == null){
            list_item = LayoutInflater.from(getContext()).inflate(R.layout.item_list_pets, parent,false);
        }

        //Traer los objetos del array correspondiente
        Pet currentPet = getItem(position);

        TextView nameTextView =(TextView) list_item.findViewById(R.id.nameTextView);
        nameTextView.setText(currentPet.getName());

        TextView breedTextView =(TextView) list_item.findViewById(R.id.breedTextView);
        breedTextView.setText(currentPet.getBreed());

        return list_item;
    }
}
