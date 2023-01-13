package com.example.practicasenerodam.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.practicasenerodam.domain.Tarea;

import java.util.List;

@Dao
public interface TareaDao {

    @Query("SELECT * FROM tarea")
    List<Tarea> getAll();

    @Query("SELECT * FROM tarea WHERE name = :name")
    Tarea getByName(String name);

    @Query("DELETE FROM tarea WHERE name = :name")
    void deleteByName(String name);

    @Insert
    void insert(Tarea tarea);

    @Delete
    void delete(Tarea tarea);

    @Update
    void update(Tarea tarea);
}
