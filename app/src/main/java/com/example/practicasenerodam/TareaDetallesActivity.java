package com.example.practicasenerodam;

import static com.example.practicasenerodam.db.Constants.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.practicasenerodam.db.AppDatabase;
import com.example.practicasenerodam.domain.Tarea;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;

import java.util.List;

public class TareaDetallesActivity extends AppCompatActivity {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    private Context context;
    private List<Tarea> taskList;

    /**
     * Vista detalle
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_detalles);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if (name == null)
            return;

        // Cargo los detalles de la tarea
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        Tarea tarea = db.tareaDao().getByName(name);
        fillData(tarea);
    }
    /**
     * Rellenar datos de la tarea seleccionada
     *
     * */
    private void fillData(Tarea tarea) {
        TextView tvName = findViewById(R.id.tv_tarea_name);
        TextView tvDescription = findViewById(R.id.tv_tarea_description);
        TextView tvOwner = findViewById(R.id.tv_tarea_owner);

        tvName.setText(tarea.getName());
        tvDescription.setText(tarea.getDescription());
        tvOwner.setText(tarea.getOwner());
    }

    public void buttonModify(View view){
        //TODO
        Intent intent = new Intent(this, RegTarActivity.class);
        startActivity(intent);
        /*
        Tarea tarea = taskList.get(position);

        Intent intent = new Intent(context, TareaDetallesActivity.class);
        intent.putExtra("name", tarea.getName());
        context.startActivity(intent);*/
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