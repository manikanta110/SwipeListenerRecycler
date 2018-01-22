package com.example.user.swipelistenerrecycler.model;

/**
 * Created by user on 22-Jan-18.
 */

public class Result {
    String name,desc;

    public Result(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
