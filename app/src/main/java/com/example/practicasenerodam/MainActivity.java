package com.example.practicasenerodam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.practicasenerodam.adapter.TareaAdapter;
import com.example.practicasenerodam.domain.Tarea;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Tarea> taskList;
    private TareaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}