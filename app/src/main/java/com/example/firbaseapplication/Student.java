package com.example.firbaseapplication;

public class Student {
    public String name;
    public String email;
    public String password;
    public String address;
    public String gender;
    public String imageurl;

    public Student (){}

    public Student(String name, String email, String password, String address, String gender, String imageurl) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
        this.imageurl = imageurl;
    }
}
