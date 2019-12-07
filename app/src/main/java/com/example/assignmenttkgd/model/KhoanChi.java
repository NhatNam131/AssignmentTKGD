package com.example.assignmenttkgd.model;

public class KhoanChi {

    private String id;
    private String name;
    private String type;
    private String money;
    private String date;
    private String description;

    public KhoanChi(){

    }

    public KhoanChi(String id, String name, String type, String money, String date, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.money = money;
        this.date = date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
