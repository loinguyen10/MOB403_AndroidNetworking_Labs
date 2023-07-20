package com.ph25590.mob403_labs.lab4;

public class Human {
    int id,age,price;
    String name;

    public Human(int id, String name, int age, int price) {
        this.id = id;
        this.age = age;
        this.price = price;
        this.name = name;
    }

    public Human() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
