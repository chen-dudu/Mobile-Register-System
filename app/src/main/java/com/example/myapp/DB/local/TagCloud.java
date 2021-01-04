package com.example.myapp.DB.local;

import cn.bmob.v3.BmobObject;

public class TagCloud extends BmobObject {

    private String name;
    private String description;

    public TagCloud(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
