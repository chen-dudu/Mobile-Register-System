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
    private String lng;
    private String lat;
    private String status;

    public RecordCloud(String province, String city, String district, String road,
                       String detail, String description, String tag, String lng, String lat, String status) {
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

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    public String getStatus() {
        return status;
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

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
