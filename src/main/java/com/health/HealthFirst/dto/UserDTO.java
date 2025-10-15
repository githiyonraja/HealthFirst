package com.health.HealthFirst.dto;

public class UserDTO {

    private String firstname;
    private String lastname;
    private Integer age;
    private Character gender;

    // Default constructor
    public UserDTO() {
    }

    // Parameterized constructor
    public UserDTO(String firstname, String lastname, Integer age, Character gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
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

}
