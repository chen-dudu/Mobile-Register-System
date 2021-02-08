package com.example.myapp.DB.local;

import cn.bmob.v3.BmobObject;

public class RecordCloud extends BmobObject {

    private String province;
    private String city;
    private String district;
    private String road;
    private String detail;
    private String description;
    private String tag;
    private double lng;
    private double lat;
    private String status;
    private String note;

    public RecordCloud(String province, String city, String district, String road,
                       String detail, String description, String tag, double lng, double lat, String status, String note) {
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
        this.note = note;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getRoad() {
        return road;
    }

    public String getDetail() {
        return detail;
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public String getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
