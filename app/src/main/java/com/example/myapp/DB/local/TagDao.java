package com.example.myapp.DB.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
interface TagDao {

    @Query("select * from tag")
    LiveData<List<Tag>> getAllTags();

    @Query("select * from tag where tag = :tag")
    LiveData<Tag> getTag(String tag);

    @Query("select * from tag where tag in (:tags)")
    LiveData<List<Tag>> getTags(String[] tags);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addTag(Tag tag);

    @Delete
    void deleteTag(Tag tag);
}
