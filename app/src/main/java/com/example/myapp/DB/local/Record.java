/**
 * this file contains the entity class, "Record", for the room database
 */
package com.example.myapp.DB.local;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Record {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    public int id;

    @ColumnInfo(name = "Province")
    @NonNull
    public String province = "";

    @ColumnInfo(name = "City")
    @NonNull
    public String city = "";

    @ColumnInfo(name = "District")
    @NonNull
    public String district = "";

    @ColumnInfo(name = "Road")
    @NonNull
    public String road = "";

    @ColumnInfo(name = "Other detail")
    @NonNull
    public String detail = "";

//    @ForeignKey(Tag.class)
    @ColumnInfo(name = "Tag")
    @NonNull
    public String tag = "";

    public Record(@NonNull String province, @NonNull String city, @NonNull String district,
                  @NonNull String road, @NonNull String detail, @NonNull String tag) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.road = road;
        this.detail = detail;
        this.tag = tag;
    }
}
