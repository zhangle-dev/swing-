package com.orderfood.pojo;

public class Menu {
    private Integer id;

    private String name;

    private Float jiage;

    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getJiage() {
        return jiage;
    }

    public void setJiage(Float jiage) {
        this.jiage = jiage;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }
}