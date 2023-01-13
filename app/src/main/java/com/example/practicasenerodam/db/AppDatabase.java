package com.example.practicasenerodam.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.practicasenerodam.domain.Tarea;

@Database(entities = {Tarea.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract TareaDao tareaDao();
}
