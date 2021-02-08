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
    @PrimaryKey
    @ColumnInfo(name = "ID")
    @NonNull
    public String id;

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

    @ColumnInfo(name = "description")
    @NonNull
    public String description = "";

//    @ForeignKey(Tag.class)
    @ColumnInfo(name = "Tag")
    @NonNull
    public String tag = "";

    @ColumnInfo(name = "Lng")
    @NonNull
    public String lng = "";

    @ColumnInfo(name = "Lat")
    @NonNull
    public String lat = "";

    @ColumnInfo(name = "Status")
    @NonNull
    public String status = "";

    public Record(@NonNull String id, @NonNull String province, @NonNull String city, @NonNull String district,
                  @NonNull String road, @NonNull String detail, @NonNull String description, @NonNull String tag,
                  @NonNull String lng, @NonNull String lat, @NonNull String status) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.district = district;
        this.road = road;
        this.detail = detail;
        this.description = description;
        this.tag = tag;
        this.lng = lng;
        this.lat = lat;
        this.status = status;
    }
}
