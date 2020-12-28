package com.example.myapp.DB.local;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecordDAO {
    @Query("select * from record")
    LiveData<List<Record>> getAllRecords();

    @Query("select * from record where ID = :rid")
    LiveData<Record> getRecordById(int rid);

    @Query("select * from record where ID in (:rids)")
    LiveData<List<Record>> getRecordsById(int[] rids);

    @Query("select * from record where Province = :province")
    LiveData<List<Record>> getRecordsByProvince(String province);

    @Query("select * from record where Province in (:provinces)")
    LiveData<List<Record>> getRecordsByProvinces(String[] provinces);

    @Query("select * from record where City = :city")
    LiveData<List<Record>> getRecordsByCity(String city);

    @Query("select * from record where City in (:cities)")
    LiveData<List<Record>> getRecordsByCities(String[] cities);

    @Query("select * from record where District = :district")
    LiveData<List<Record>> getRecordsByDistrict(String district);

    @Query("select * from record where District in (:districts)")
    LiveData<List<Record>> getRecordsByDistricts(String[] districts);

    @Query("select * from record where Tag = :tag")
    LiveData<List<Record>> getRecordsByTag(String tag);

    @Query("select * from record where Tag in (:tags)")
    LiveData<List<Record>> getRecordsByTags(String[] tags);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addRecord(Record record);

    @Update
    void update(Record record);

    @Delete
    void deleteRecord(Record record);

    @Query("delete from record")
    void deleteAll();
}
