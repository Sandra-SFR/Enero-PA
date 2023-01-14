package com.example.practicasenerodam;

import static com.example.practicasenerodam.db.Constants.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicasenerodam.adapter.TareaAdapter;
import com.example.practicasenerodam.db.AppDatabase;
import com.example.practicasenerodam.domain.Tarea;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class RegTarActivity extends AppCompatActivity {

    private Context context;
    private List<Tarea> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_tar);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if (name == null)
            return;

        // Cargo los detalles de la tarea
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        Tarea tarea = db.tareaDao().getByName(name);
        fillData(tarea);
        modificar(tarea);

    }

    //TODO rellenar los datos antiguos
    public void fillData(Tarea tarea){
        EditText etName = findViewById(R.id.edit_text_name);
        EditText etDescription = findViewById(R.id.edit_text_description);
        EditText etOwner = findViewById(R.id.edit_text_owner);

        etName.setText(tarea.getName());
        etDescription.setText(tarea.getDescription());
        etOwner.setText(tarea.getOwner());
    }
    /**
    * Vista Modificar
     * boton de guardar
    * */

    //TODO en vez de guardar uno nuevo que haga un update
    public void saveButton(View view) {
        EditText etName = findViewById(R.id.edit_text_name);
        EditText etDescription = findViewById(R.id.edit_text_description);
        EditText etOwner = findViewById(R.id.edit_text_owner);

        String name = etName.getText().toString();
        String description = etDescription.getText().toString();
        String owner = etOwner.getText().toString();

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
               .allowMainThreadQueries().build();

        try {
            db.tareaDao().update(name, description, owner);

            Toast.makeText(this, R.string.tarea_registered_message, Toast.LENGTH_LONG).show();
            etDescription.setText("");
            etOwner.setText("");
            etDescription.requestFocus();
        } catch (SQLiteConstraintException sce) {
            Snackbar.make(etName, R.string.tarea_registered_error, BaseTransientBottomBar.LENGTH_LONG).show();
            sce.printStackTrace();
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