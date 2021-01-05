package com.example.myapp.DB.local;

import cn.bmob.v3.BmobObject;

public class RecordCloud extends BmobObject {

    private String id;
    private String province;
    private String city;
    private String district;
    private String road;
    private String detail;
    private String description;
    private String tag;

    public RecordCloud(String id, String province, String city, String district, String road,
                       String detail, String description, String tag) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.district = district;
        this.road = road;
        this.detail = detail;
        this.description = description;
        this.tag = tag;
    }

    public String getId() {
        return id;
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
}