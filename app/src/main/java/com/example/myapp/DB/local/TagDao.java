package com.example.myapp.DB.local;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
interface TagDao {

    @Query("select * from tag")
    List<Tag> getAllTags();

    @Query("select * from tag where tag = :tag")
    Tag getTag(String tag);

    @Query("select * from tag where tag in (:tags)")
    List<Tag> getTags(String[] tags);
}
