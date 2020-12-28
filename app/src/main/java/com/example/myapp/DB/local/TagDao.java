package com.example.myapp.DB.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TagDao {

    @Query("select * from tag order by `Tag name`")
    LiveData<List<Tag>> getAllTags();

    @Query("select * from tag where `Tag name` = :tag")
    LiveData<Tag> getTag(String tag);

    @Query("select * from tag where `Tag name` in (:tags)")
    LiveData<List<Tag>> getTags(String[] tags);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addTag(Tag tag);

    @Update
    void updateDescription(Tag tag);

    @Delete
    void deleteTag(Tag tag);

    @Query("delete from tag")
    void deleteAll();
}
