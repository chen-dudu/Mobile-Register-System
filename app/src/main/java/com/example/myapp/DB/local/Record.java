package com.example.myapp.DB.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Record {
    @PrimaryKey
    public int ID;

    @ColumnInfo(name = "Province")
    public String province;

    @ColumnInfo(name = "City")
    public String city;

    @ColumnInfo(name = "District")
    public String district;

    @ColumnInfo(name = "Road")
    public String road;

    @ColumnInfo(name = "Other detail")
    public String detail;
}
