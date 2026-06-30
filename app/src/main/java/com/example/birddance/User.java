package com.example.birddance;

public class User {
    private String idNumber;
    private String name;
    private Integer score;

    // Required no-argument constructor for Firestore
    public User() {}

    // Constructor with parameters
    public User(String idNumber, String name, Integer score) {
        this.idNumber = idNumber;
        this.name = name;
        this.score = score;
    }

    // Getters and Setters
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
