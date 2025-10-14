package com.health.HealthFirst.dto;

public class UserDTO {

    private String firstname;
    private String lastname;
    private Integer age;
    private Character gender;
    private Double weight;
    private Double height;

    // Default constructor
    public UserDTO() {
    }

    // Parameterized constructor
    public UserDTO(String firstname, String lastname, Integer age, Character gender, Double weight, Double height) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    // Getters and Setters
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Character getGender() {
        return gender;
    }
    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }
    public void setHeight(Double height) {
        this.height = height;
    }
}
