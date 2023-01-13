package com.example.practicasenerodam.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Tarea {

    @PrimaryKey
    @NonNull
    private String name;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private String owner;
    @ColumnInfo
    private boolean done;
    @ColumnInfo
    private double latitude;
    @ColumnInfo
    private double longitude;


    public Tarea() {}

    public Tarea(String name, String description, String owner, boolean done, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.done = done;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
