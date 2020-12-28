/**
 * this file contains the entity class, "Tag", for the room database
 */
package com.example.myapp.DB.local;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tag {
    @PrimaryKey
    @ColumnInfo(name = "Tag name")
    @NonNull
    public String name;

    @ColumnInfo(name = "Tag description")
    @Nullable
    public String description;

    public Tag(@NonNull String name, @Nullable String description) {
        this.name = name;
        this.description = description;
    }

//    public Tag(String name) {
//        this.name = name;
//    }
}
