package com.example.myapp.DB.local;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
interface RecordDAO {
    @Query("select * from record")
    List<Record> getAllRecords();

    @Query("select * from record where ID = :rid")
    Record getRecordById(int rid);

    @Query("select * from record where ID in (:rids)")
    List<Record> getRecordsById(int[] rids);

    @Query("select * from record where Province = :province")
    List<Record> getRecordsByProvince(String province);

    @Query("select * from record where Province in (:provinces)")
    List<Record> getRecordsByProvinces(String[] provinces);

    @Query("select * from record where City = :city")
    List<Record> getRecordsByCity(String city);

    @Query("select * from record where City in (:cities)")
    List<Record> getRecordsByCities(String[] cities);

    @Query("select * from record where District = :district")
    List<Record> getRecordsByDistrict(String district);

    @Query("select * from record where District in (:districts)")
    List<Record> getRecordsByDistricts(String[] districts);


}
