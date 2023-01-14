package com.example.practicasenerodam;

import static com.example.practicasenerodam.db.Constants.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practicasenerodam.db.AppDatabase;
import com.example.practicasenerodam.domain.Tarea;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class RegTarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_tar);

    }

    /**
    * Vista Registrar sin mapa
     * boton de guardar
    * */
    public void saveButton(View view) {
        EditText etName = findViewById(R.id.edit_text_name);
        EditText etDescription = findViewById(R.id.edit_text_description);
        EditText etOwner = findViewById(R.id.edit_text_owner);

        String name = etName.getText().toString();
        String description = etDescription.getText().toString();
        String owner = etOwner.getText().toString();

        Tarea tarea = new Tarea(name, description, owner, false);
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        //TODO no meter registros vacios
        /*
        if (etName.toString().equals("") ){
            Toast.makeText(this, R.string.not_empty_message, Toast.LENGTH_LONG).show();
            return;
        }
        */
        try {

            db.tareaDao().insert(tarea);

            Toast.makeText(this, R.string.tarea_registered_message, Toast.LENGTH_LONG).show();
            etName.setText("");
            etDescription.setText("");
            etOwner.setText("");
            etName.requestFocus();
        } catch (SQLiteConstraintException sce) {
            Snackbar.make(etName, R.string.tarea_registered_error, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }

    /**
     * 
     * boton de volver
     * */
    public void goBackButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);;
    }
}